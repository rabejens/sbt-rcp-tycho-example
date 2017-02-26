package com.example.binco.eclipse.ui

import scala.language.implicitConversions
import scala.language.postfixOps

import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text

import com.example.binco.BinomialCoefficient

import javax.annotation.PostConstruct

final class BinCoPart {

  private implicit def funcToSelectionAdapter[T](func: SelectionEvent => T): SelectionListener =
    new SelectionAdapter {

      override def widgetSelected(evt: SelectionEvent): Unit = func(evt)
    }

  @PostConstruct
  def postConstruct(parent: Composite): Unit = {
    parent.setLayout(new GridLayout(2, false))
    new Label(parent, SWT.NONE).setText("n")
    val tbN = new Text(parent, SWT.NONE)
    tbN.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false))
    new Label(parent, SWT.NONE).setText("k")
    val tbK = new Text(parent, SWT.NONE)
    tbK.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false))
    new Label(parent, SWT.NONE).setText("b(n, k)")
    val tbRes = new Text(parent, SWT.NONE)
    tbRes.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false))
    tbRes.setEditable(false)
    val btnData = new GridData(SWT.FILL, SWT.TOP, true, false)
    btnData.horizontalSpan = 2
    btnData.verticalIndent = 5
    val btnCalc = new Button(parent, SWT.PUSH)
    btnCalc.setLayoutData(btnData)
    btnCalc.setText("Calculate")
    btnCalc.addSelectionListener((evt: SelectionEvent) => {
      try {
        val n = tbN.getText.toLong
        val k = tbK.getText.toLong
        val res = n over k
        tbRes.setText(res.toString())
      } catch {
        case e: NumberFormatException =>
          tbRes.setText("Cannot parse number: %s" format e.getMessage)
      }
    })
  }
}
