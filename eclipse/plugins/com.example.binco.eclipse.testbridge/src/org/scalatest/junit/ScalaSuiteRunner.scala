package org.scalatest.junit

import java.lang.reflect.Modifier

import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import org.scalatest.Args
import org.scalatest.ConfigMap
import org.scalatest.Filter
import org.scalatest.Stopper
import org.scalatest.Suite
import org.scalatest.Tracker

final class ScalaSuiteRunner(clazz: Class[_]) extends Runner {

  private lazy val _suites: Iterable[Suite] = clazz.getAnnotation(classOf[ScalaTests]) match {
    case null => Seq()
    case a =>
      def hasPublicNoArgConstructor(clazz: java.lang.Class[_]) = {

        try {
          val constructor = clazz.getConstructor(new Array[java.lang.Class[T] forSome { type T }](0): _*)

          Modifier.isPublic(constructor.getModifiers)
        } catch {
          case nsme: NoSuchMethodException => false
        }
      }
      a.
        value().
        distinct.
        map { sc =>
          require(hasPublicNoArgConstructor(sc), "%s has no public default constructor" format sc.getName)
          sc.newInstance.asInstanceOf[Suite]
        }
  }

  override def getDescription(): Description = {
    def createDescription(suite: Suite): Description = {
      val description = Description.createSuiteDescription(suite.getClass)
      for (name <- suite.testNames) {
        description.addChild(Description.createTestDescription(suite.getClass, name))
      }
      for (nestedSuite <- suite.nestedSuites) {
        description.addChild(createDescription(nestedSuite))
      }
      description
    }
    val desc = Description.createSuiteDescription(getClass)
    _suites.map(createDescription).foreach(desc.addChild)
    desc
  }

  override def run(notifier: RunNotifier): scala.Unit = {
    try {
      _suites.
        foreach { suiteToRun =>
          suiteToRun.run(None, Args(new RunNotifierReporter(notifier),
            Stopper.default, Filter(), ConfigMap.empty, None,
            new Tracker, Set.empty))
        }
    } catch {
      case e: Exception =>
        notifier.fireTestFailure(new Failure(getDescription, e))
    }
  }
}
