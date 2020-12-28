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
		} else {
			action = new MainAction();
		}
		return action;
	}

}
