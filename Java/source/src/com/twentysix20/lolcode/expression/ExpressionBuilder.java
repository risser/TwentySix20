/******************************************************************************
*
* Proprietary and Confidential
* Ohio Farmers Insurance Company
*
* This document contains material which is the proprietary and confidential
* property of Ohio Farmers Insurance Company.
*
* The right to view, reproduce, modify, distribute, or in any way display
* this work is prohibited without the express written consent of the Ohio
* Farmers Insurance Company.
*
* Copyright (c) 2007 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on May 31, 2007
*/
package com.twentysix20.lolcode.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.twentysix20.lolcode.Univar;
import com.twentysix20.lolcode.exception.ExpressionBuilderException;
import com.twentysix20.util.StringUtil;

public class ExpressionBuilder {
    private String originalString;
    private List expressions = new ArrayList();
    private Iterator iter;

    public ExpressionBuilder(String original) {
        if (StringUtil.isEmpty(original))
            throw new ExpressionBuilderException("Can't build expression with empty string!", original);
       this.originalString = original.trim();
       generateExpressions();
    }

    public String getOriginalString() {
        return originalString;
    }

    public Expression getNextExpression() {
        return (Expression)iter.next();
    }

    public boolean hasMore() {
        return iter.hasNext();
    }

    public int count() {
        return expressions.size();
    }

    public void reset() {
        iter = expressions.iterator();
    }

    public void generateExpressions() {
        expressions = new ArrayList();
        String line = this.originalString;
        while (line.trim().length() > 0) {
            line = line.trim();
            if (line.startsWith("\"")) {
                int pos = line.indexOf('"',1);
                while ((pos >= 0) && (line.charAt(pos-1) == '\\'))
                    pos = line.indexOf('"', pos+1);
                if (pos < 0)
                    throw new ExpressionBuilderException("Missing closing quotes on string literal.",line);
                expressions.add(new ValueExpression(line.substring(1, pos)));
                line = line.substring(pos+1).trim();
            } else {
                String[] ss = line.split("\\s",2);
                String val = ss[0];
                if (val.matches("\\-?[0-9]+(\\.[0-9]+)?") || val.equals(Univar.TRUE) || val.equals(Univar.FALSE)) {
                    // it's a number or bool
                    expressions.add(new ValueExpression(val));
                    line = (ss.length == 1 ? "" : ss[1]);
                } else {
                    throw new ExpressionBuilderException("Bad Expression Juju!", line);
                }
            }
        }
        iter = expressions.iterator();
    }
}