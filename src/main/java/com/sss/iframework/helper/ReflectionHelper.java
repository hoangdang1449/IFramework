package com.sss.iframework.helper;

import com.sss.iframework.exception.IFrameworkBaseException;

import java.lang.reflect.Method;

public class ReflectionHelper {

  public static Object invokeFuntion(Object comp, String funcName) {
    try {
      Object retObj = null;
      Class<?>[] argType = null;
      Object[] argLst = null;
      Method myMethod = comp.getClass().getDeclaredMethod(funcName, argType);
      if (myMethod != null) {
        myMethod.setAccessible(true);
        retObj = myMethod.invoke(comp, argLst);
      }

      return retObj;
    } catch (Exception ex) {
      return null;
    }
  }

  public static Object invokeFuntionInt(Object comp, String funcName, int index) {
    try {
      Object retObj = null;
      Class<?> artypes[] = new Class[1];
      artypes[0] = Integer.TYPE;
      Object arglist[] = new Object[1];
      arglist[0] = index;

      Method myMethod = comp.getClass().getMethod(funcName, artypes);

      retObj = myMethod.invoke(comp, arglist);

      return retObj;
    } catch (Exception ex) {
      return null;
    }
  }

  public static Object invokeFunctionStr(
      Object objTarget, String funcName, String strInput, boolean ignoreCaseSensitive) {
    try {
      Object retFindNode = null;
      Class<?> mypartypes[] = new Class[1];
      mypartypes[0] = String.class;

      Method methodInvoke = getMethod(objTarget, funcName, ignoreCaseSensitive, String.class);
      if (methodInvoke == null) return null;

      Object myarglist[] = new Object[1];
      myarglist[0] = strInput;
      retFindNode = methodInvoke.invoke(objTarget, myarglist);

      return retFindNode;
    } catch (Exception ex) {
      return null;
    }
  }

  public static Object invokeFunctionEx(
      Object objToInvoke, String strFuncName, Object... ClassNObjectInputs) {
    Method mtToInvoke = null;
    Object objValue = null;
    int intNumberOfArg = -1, j = -1;

    try {
      // we can invoke functions without input params by passing "null" or
      // not passing any thing at all.
      // ex : Invoke.InvokeFunctionEx(oneObject, "FunctionName") Or
      // Invoke.InvokeFunctionEx(oneObject, "FunctionName", null)
      Boolean blIsNoParamFunction =
			  ClassNObjectInputs == null || ClassNObjectInputs.length == 0;

      if (objToInvoke == null) throw new IFrameworkBaseException("Invalid object input.");

      if (!(strFuncName != null && strFuncName.length() > 0))
        throw new IFrameworkBaseException("Invalid function name.");

      if (!blIsNoParamFunction) {
        if (!(ClassNObjectInputs.length % 2 == 0))
          throw new IFrameworkBaseException("Invalid number of class/object input.");

        for (int i = 0; i < ClassNObjectInputs.length / 2; i++)
          if (!(ClassNObjectInputs[i] instanceof Class))
            throw new IFrameworkBaseException("Invalid Class order.");

        // begin processing...
        intNumberOfArg = ClassNObjectInputs.length / 2;
      }

      Class<?>[] clsInputsN = null;
      Object[] objInputsN = null;

      if (!blIsNoParamFunction) {
        clsInputsN = new Class[intNumberOfArg];
        objInputsN = new Object[intNumberOfArg];

        for (int i = 0; i < intNumberOfArg; i++) clsInputsN[i] = (Class<?>) ClassNObjectInputs[i];

        j = 0;
        for (int i = intNumberOfArg; i < intNumberOfArg * 2; i++)
          objInputsN[j++] = ClassNObjectInputs[i];
      }
      mtToInvoke =
          objToInvoke
              .getClass()
              .getMethod(strFuncName, (!blIsNoParamFunction ? clsInputsN : null));
      if (mtToInvoke != null) {
        mtToInvoke.setAccessible(true);

        objValue =
            mtToInvoke.invoke(objToInvoke, (!blIsNoParamFunction ? objInputsN : null));

      } else {
        objValue = null;
      }

    } catch (Exception ex) {
      objValue = null;
    }
    return objValue;
  }

  public static Method getMethod(
      Object objTarget, String methodName, boolean ignoreCaseSensitive, Class<?>... paramTypes) {
    Method method = null;

    if (objTarget != null) {

      try {
        if (!ignoreCaseSensitive) {
          method = objTarget.getClass().getDeclaredMethod(methodName, paramTypes);
          method.setAccessible(true);
        } else {
          Method[] methods = objTarget.getClass().getDeclaredMethods();

          for (Method mthd : methods) {
            boolean isEqualMethodName = mthd.getName().equalsIgnoreCase(methodName);
            if (isEqualMethodName) {
              Class<?>[] params = mthd.getParameterTypes();
              boolean equalNumberParams = (params.length) == (paramTypes.length);
              if (equalNumberParams) {
                for (int i = 0; i < params.length; i++) {
                  if (params[i].equals(paramTypes[i])) {
                    method = mthd;
                    method.setAccessible(true);
                    break;
                  }
                } //end for
              }
            }
          } //end for
        }
      } catch (NoSuchMethodException e) {
        return null;
      } catch (SecurityException e) {
        return null;
      }
    }

    return method;
  }
}
