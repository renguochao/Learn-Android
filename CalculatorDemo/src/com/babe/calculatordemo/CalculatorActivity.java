package com.babe.calculatordemo;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends Activity implements OnClickListener {
	private EditText et_input;

	private Button btn_cancel;
	private Button btn_del;
	private Button btn_divide;
	private Button btn_multiply;
	private Button btn_plus;
	private Button btn_minus;
	private Button btn_equal;

	private Button btn_0;
	private Button btn_1;
	private Button btn_2;
	private Button btn_3;
	private Button btn_4;
	private Button btn_5;
	private Button btn_6;
	private Button btn_7;
	private Button btn_8;
	private Button btn_9;
	private Button btn_dot;

	private boolean clear_flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);

		btn_0 = (Button) findViewById(R.id.btn_zero);
		btn_1 = (Button) findViewById(R.id.btn_one);
		btn_2 = (Button) findViewById(R.id.btn_two);
		btn_3 = (Button) findViewById(R.id.btn_three);
		btn_4 = (Button) findViewById(R.id.btn_four);
		btn_5 = (Button) findViewById(R.id.btn_five);
		btn_6 = (Button) findViewById(R.id.btn_six);
		btn_7 = (Button) findViewById(R.id.btn_seven);
		btn_8 = (Button) findViewById(R.id.btn_eight);
		btn_9 = (Button) findViewById(R.id.btn_nine);
		btn_dot = (Button) findViewById(R.id.btn_dot);

		btn_divide = (Button) findViewById(R.id.btn_divide);
		btn_multiply = (Button) findViewById(R.id.btn_multiply);
		btn_plus = (Button) findViewById(R.id.btn_plus);
		btn_minus = (Button) findViewById(R.id.btn_minus);
		btn_equal = (Button) findViewById(R.id.btn_equal);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_del = (Button) findViewById(R.id.btn_del);

		et_input = (EditText) findViewById(R.id.input);
		// set inputDisplay to 0 when the calculator has been started.
		et_input.setText("0");

		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_dot.setOnClickListener(this);

		btn_divide.setOnClickListener(this);
		btn_multiply.setOnClickListener(this);
		btn_plus.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_equal.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		btn_del.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		String str = et_input.getText().toString();
		switch (v.getId()) {
		case R.id.btn_zero:
		case R.id.btn_one:
		case R.id.btn_two:
		case R.id.btn_three:
		case R.id.btn_four:
		case R.id.btn_five:
		case R.id.btn_six:
		case R.id.btn_seven:
		case R.id.btn_eight:
		case R.id.btn_nine:
		case R.id.btn_dot:
			if (clear_flag) {
				et_input.setText(((Button) v).getText());
				clear_flag = false;
			} else {
				if (str.equals("0")) {
					et_input.setText(((Button) v).getText());
				} else {
					et_input.setText(str + ((Button) v).getText());
				}
			}
			break;
		case R.id.btn_plus:
		case R.id.btn_minus:
		case R.id.btn_multiply:
		case R.id.btn_divide:
			if (clear_flag)
				clear_flag = !clear_flag;
			et_input.setText(str + " " + ((Button) v).getText() + " ");
			break;
		case R.id.btn_cancel:
			et_input.setText("0");
			break;
		case R.id.btn_del:
			// because we add blank space around the operator
			// when we click the del button, we need to check
			// whether the last character is operator or not.
			// if the last character is an operator we alse need
			// to remove all these space added around the operator.
			// otherwise, we only need to remove the digit.
			if(str.charAt(str.length() - 1) == ' ' && str.length() >= 3) {
				et_input.setText(str.substring(0, str.length() - 3));
			} else {
				et_input.setText(str.substring(0, str.length() - 1));
			}
			break;
		case R.id.btn_equal:
//			getResult();
			getResultByUsingExpCalc();
			clear_flag = true;
			break;
		default:
			break;
		}
	}
	

	private void getResultByUsingExpCalc() {
		String str = et_input.getText().toString();
		
		ExpCalc ec = new ExpCalc();
		double result = ec.calcExp(str);
		
		et_input.setText(Double.toString(result));
	}

	private void getResult() {
		String str = et_input.getText().toString();
		if (str == null || str.equals(""))
			return;

		if (!str.contains(" "))
			return;

		String operand1 = str.substring(0, str.indexOf(" "));
		String operator = str.substring(str.indexOf(" ") + 1,
				str.indexOf(" ") + 2);
		String operand2 = str.substring(str.indexOf(" ") + 3, str.length());

		if (operand1.equals("") && operand2.equals("")) {
			et_input.setText("0");
			return;
		}

		if (operand1.equals("") && !operand2.equals("")) {
			String result = "";
			if (operand2.contains(".")) {
				// double
				double d2 = Double.parseDouble(operand2);
				if (operator.equals("+")) {
					result = Double.toString(d2);
				} else if (operator.equals("-")) {
					result = Double.toString(0 - d2);
				} else if (operator.equals("¡Â")) {
					result = Double.toString(0);
				} else {
					result = Double.toString(0);
				}
			} else {
				// int
				int d2 = Integer.parseInt(operand2);
				if (operator.equals("+")) {
					result = Integer.toString(d2);
				} else if (operator.equals("-")) {
					result = Integer.toString(0 - d2);
				} else if (operator.equals("¡Â")) {
					result = Integer.toString(0);
				} else {
					result = Integer.toString(0);
				}
			}

			et_input.setText(result);
			return;
		}

		if (!operand1.equals("") && operand2.equals("")) {
			String result = "";
			if (operand1.contains(".")) {
				// double
				double d1 = Double.parseDouble(operand1);
				if (operator.equals("+")) {
					result = Double.toString(d1);
				} else if (operator.equals("-")) {
					result = Double.toString(d1);
				} else if (operator.equals("¡Â")) {
					result = "¡Þ";
				} else {
					result = Double.toString(0);
				}
			} else {
				// int
				int d1 = Integer.parseInt(operand1);
				if (operator.equals("+")) {
					result = Integer.toString(d1);
				} else if (operator.equals("-")) {
					result = Integer.toString(d1);
				} else if (operator.equals("¡Â")) {
					result = "¡Þ";
				} else {
					result = Integer.toString(0);
				}
			}

			et_input.setText(result);
			return;
		}

		if (!operand1.equals("") && !operand2.equals("")) {
			String result = "";

			if (operand1.contains(".") || operand2.contains(".")) {
				double d1 = Double.parseDouble(operand1);
				double d2 = Double.parseDouble(operand2);
				Log.i("babe", "d1:" + d1);
				Log.i("babe", "d2:" + d2);
				if (operator.equals("+")) {
					result = Double.toString(d1 + d2);
				} else if (operator.equals("-")) {
					result = Double.toString(d1 - d2);
				} else if (operator.equals("¡Â")) {
					if (Math.abs(d2) < 1e-6) {
						result = "¡Þ";
					}
					result = Double.toString(d1 / d2);
				} else {
					result = Double.toString(d1 * d2);
				}
			} else {
				int d1 = Integer.parseInt(operand1);
				int d2 = Integer.parseInt(operand2);

				if (operator.equals("+")) {
					result = Integer.toString(d1 + d2);
				} else if (operator.equals("-")) {
					result = Integer.toString(d1 - d2);
				} else if (operator.equals("¡Â")) {
					if (d2 == 0) {
						et_input.setText("¡Þ");
						return;
					} 
					if (d1 % d2 != 0) {
						result = Double.toString(d1 / (double) d2);
					} else {
						result = Integer.toString(d1 / d2);
					}
				} else {
					result = Integer.toString(d1 * d2);
				}
			}

			et_input.setText(result);
		}

	}

}
