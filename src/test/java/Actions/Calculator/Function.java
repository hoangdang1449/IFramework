package Actions.Calculator;

import Interfaces.Calculator.CalculatorEntity;
import com.sss.selenium.IFrameworkAssert;
import com.sss.selenium.IFrameworkAutomation;
import com.sss.selenium.IFrameworkElementDefinition;
import org.openqa.selenium.By;

public class Function {
	private static Function instance = null;
	public static synchronized Function getFunction()
	{
		if(instance == null)
			instance = new Function();
		return instance;
	}
	public void plusTwoNumber(String numberA, String numberB){
		char[] charArrayA = numberA.toCharArray();
		for (char c : charArrayA) {
			IFrameworkAutomation.click(IFrameworkElementDefinition.Name(Character.toString(c)));
		}
		IFrameworkAutomation.click(CalculatorEntity.Button_Plus);
		char[] charArrayB = numberB.toCharArray();
		for (char c : charArrayB) {
			IFrameworkAutomation.click(IFrameworkElementDefinition.Name(Character.toString(c)));
		}
		IFrameworkAutomation.click(CalculatorEntity.Button_Equal);
	}
	public void minusTwoNumber(String numberA, String numberB){
		char[] charArrayA = numberA.toCharArray();
		for (char c : charArrayA) {
			IFrameworkAutomation.click(IFrameworkElementDefinition.Name(Character.toString(c)));
		}
		IFrameworkAutomation.click(CalculatorEntity.Button_Minus);
		char[] charArrayB = numberB.toCharArray();
		for (char c : charArrayB) {
			IFrameworkAutomation.click(IFrameworkElementDefinition.Name(Character.toString(c)));
		}
		IFrameworkAutomation.click(CalculatorEntity.Button_Equal);
	}
	public void multiTwoNumber(String numberA, String numberB){
		char[] charArrayA = numberA.toCharArray();
		for (char c : charArrayA) {
			IFrameworkAutomation.click(IFrameworkElementDefinition.Name(Character.toString(c)));
		}
		IFrameworkAutomation.click(CalculatorEntity.Button_Multi);
		char[] charArrayB = numberB.toCharArray();
		for (char c : charArrayB) {
			IFrameworkAutomation.click(IFrameworkElementDefinition.Name(Character.toString(c)));
    }
		IFrameworkAutomation.click(CalculatorEntity.Button_Equal);
	}
	public void divisionTwoNumber(String numberA, String numberB){
		char[] charArrayA = numberA.toCharArray();
		for (char c : charArrayA) {
			IFrameworkAutomation.click(IFrameworkElementDefinition.Name(Character.toString(c)));
		}
		IFrameworkAutomation.click(CalculatorEntity.Button_Division);
		char[] charArrayB = numberB.toCharArray();
		for (char c : charArrayB) {
			IFrameworkAutomation.click(IFrameworkElementDefinition.Name(Character.toString(c)));
		}
		IFrameworkAutomation.click(CalculatorEntity.Button_Equal);
	}
	public void CheckResultDisplayed(By element, String result){
		String text = IFrameworkAutomation.getText(element);
		IFrameworkAssert.verifyEquals(text, result);
	}
}
