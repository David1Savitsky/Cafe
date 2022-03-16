package com.epam.webappfinal.command;

import com.epam.webappfinal.dao.DaoHelperFactory;
import com.epam.webappfinal.service.FoodServiceImpl;
import com.epam.webappfinal.service.UserServiceImpl;

public class CommandFactory {

    private static final String LOGIN_COMMAND = "login";
    private static final String LOGOUT_COMMAND = "logout";
    private static final String MAIN_PAGE_COMMAND = "mainPage";
    private static final String LOGIN_AFTER_LOGOUT_COMMAND = "loginAfterLogout";
    private static final String LANGUAGE_CHANGE_COMMAND = "languageChange";
    private static final String FOOD_TYPE_CHANGE_COMMAND = "foodChange";
    private static final String FILL_UP_MONEY_COMMAND = "fillUpMoney";
    private static final String CHANGE_Q_INDEX_COMMAND = "changeQIndex";
    private static final String SHOPPING_CART_COMMAND = "shoppingCart";

    public Command createCommand(String command) {
        switch (command){
            case LOGIN_COMMAND:
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case LOGOUT_COMMAND:
                return new LogoutCommand();
            case MAIN_PAGE_COMMAND:
                return new MainPageCommand(new FoodServiceImpl(new DaoHelperFactory()));
            case LOGIN_AFTER_LOGOUT_COMMAND:
                return new LoginAfterLogoutCommand();
            case LANGUAGE_CHANGE_COMMAND:
                return new LanguageChangeCommand();
            case FOOD_TYPE_CHANGE_COMMAND:
                return new FoodTypeChangeCommand(new FoodServiceImpl(new DaoHelperFactory()));
            case FILL_UP_MONEY_COMMAND:
                return new FillUpMoneyCommand(new UserServiceImpl(new DaoHelperFactory()));
            case CHANGE_Q_INDEX_COMMAND:
                return new ChangeQIndexCommand();
            case SHOPPING_CART_COMMAND:
                return new ShoppingCartCommand();
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
