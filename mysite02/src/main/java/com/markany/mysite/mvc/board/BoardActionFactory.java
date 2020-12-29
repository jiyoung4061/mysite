package com.markany.mysite.mvc.board;

import com.markany.mysite.mvc.main.MainAction;
import com.markany.web.mvc.Action;
import com.markany.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("list".equals(actionName)) {
			action = new ListAction();
		} else if("addform".equals(actionName)) {
			action = new AddFormAction();
		} else if("add".equals(actionName)) {
			action = new AddAction();
		} else if("viewform".equals(actionName)) {
			action = new ViewFormAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else {
			action = new MainAction();
		}
		return action;
	}

}
