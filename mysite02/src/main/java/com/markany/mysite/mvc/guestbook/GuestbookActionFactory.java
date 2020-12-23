package com.markany.mysite.mvc.guestbook;

import com.markany.mysite.mvc.main.MainAction;
import com.markany.web.mvc.Action;
import com.markany.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		System.out.println("실행되나");
		if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if("list".equals(actionName)) {
			action = new ListAction();
		} else if("add".equals(actionName)) {
			action = new AddAction();
		} else {
			action = new MainAction();
		}
		
		return action;
	}

}
