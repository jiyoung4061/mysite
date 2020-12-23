package com.markany.mysite.mvc.main;

import com.markany.web.mvc.Action;
import com.markany.web.mvc.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}
}
