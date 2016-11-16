package Interfaces.Calculator;

import com.sss.selenium.IFrameworkElementDefinition;
import org.openqa.selenium.By;

public class CalculatorEntity {
	public static By Button_One = IFrameworkElementDefinition.Name("1");
	public static By Button_Two = IFrameworkElementDefinition.Name("2");
	public static By Button_Three = IFrameworkElementDefinition.Name("3");
	public static By Button_Four = IFrameworkElementDefinition.Name("4");
	public static By Button_Five = IFrameworkElementDefinition.Name("5");
	public static By Button_Six = IFrameworkElementDefinition.Name("6");
	public static By Button_Seven = IFrameworkElementDefinition.Name("7");
	public static By Button_Eight = IFrameworkElementDefinition.Name("8");
	public static By Button_Nine = IFrameworkElementDefinition.Name("9");
	public static By Button_Zero = IFrameworkElementDefinition.Name("0");
	public static By Button_C = IFrameworkElementDefinition.Name("C");
	public static By Button_MC = IFrameworkElementDefinition.Name("MC");
	public static By Button_M_Plus = IFrameworkElementDefinition.Name("M+");
	public static By Button_M_Minus = IFrameworkElementDefinition.Name("M-");
	public static By Button_MR = IFrameworkElementDefinition.Name("MR");
	public static By Button_Multi = IFrameworkElementDefinition.Name("×");
	public static By Button_Minus = IFrameworkElementDefinition.Name("−");
	public static By Button_Plus = IFrameworkElementDefinition.Name("+");
	public static By Button_Division = IFrameworkElementDefinition.Name("÷");
	public static By Button_Equal = IFrameworkElementDefinition.Name("=");
	public static By Button_Sign = IFrameworkElementDefinition.Name("±");
	public static By Button_Dot = IFrameworkElementDefinition.Name(".");
	public static By Label_MainResult = IFrameworkElementDefinition.Xpath("//AXStaticText[@AXDescription='main display']");
}
