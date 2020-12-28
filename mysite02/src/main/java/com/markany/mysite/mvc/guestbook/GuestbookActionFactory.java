package com.markany.mysite.mvc.guestbook;

import com.markany.mysite.mvc.main.MainAction;
import com.markany.web.mvc.Action;
import com.markany.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if("list".equals(actionName)) {
			action = new ListAction();
		} else if("add".equals(actionName)) {
			action = new AddAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {
			action = new MainAction();
		}
		
		return action;
	}

}
