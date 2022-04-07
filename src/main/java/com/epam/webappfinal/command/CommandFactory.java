package com.epam.webappfinal.command;

import com.epam.webappfinal.dao.DaoHelperFactory;
import com.epam.webappfinal.service.FoodServiceImpl;
import com.epam.webappfinal.service.OrderServiceImpl;
import com.epam.webappfinal.service.UserServiceImpl;

public class CommandFactory {

    private static final String LOGIN_COMMAND = "login";
    private static final String LOGOUT_COMMAND = "logout";
    private static final String MAIN_PAGE_COMMAND = "mainPage";
    private static final String LOGIN_AFTER_LOGOUT_COMMAND = "loginAfterLogout";
    private static final String LANGUAGE_CHANGE_COMMAND = "languageChange";
    private static final String FOOD_TYPE_CHANGE_COMMAND = "foodChange";
    private static final String FILL_UP_MONEY_COMMAND = "fillUpMoney";
    private static final String SHOPPING_CART_COMMAND = "shoppingCart";
    private static final String ADD_TO_SHOPPING_CART_COMMAND = "addToShoppingCart";
    private static final String DELETE_FROM_SHOPPING_CART_COMMAND = "deleteFromShopCart";
    private static final String INCREMENT_FOOD_IN_SHOPPING_CART_COMMAND = "incrementFoodInShopCart";
    private static final String DECREMENT_FOOD_IN_SHOPPING_CART_COMMAND = "decrementFoodInShopCart";
    private static final String PAY_ORDER_COMMAND = "payOrder";
    private static final String DELETE_FOOD_ITEM_COMMAND = "deleteFoodItem";
    private static final String SAVE_FOOD_ITEM_COMMAND = "saveFoodItem";
    private static final String ADD_FOOD_ITEM_COMMAND = "addFoodItem";
    private static final String USERS_COMMAND = "users";
    private static final String CHANGE_BLOCK_COMMAND = "changeBlock";
    private static final String CHANGE_LOYALTY_POINTS_COMMAND = "changeLoyaltyPoints";
    private static final String ORDERS_COMMAND = "orders";
    private static final String CHANGE_RATING_COMMAND = "changeRating";
    private static final String ORDER_IS_TAKEN_COMMAND = "orderIsTaken";
    private static final String ORDER_IS_REJECTED_COMMAND = "orderIsRejected";
    private static final String RATING_COMMAND = "rating";
    private static final String ADD_COMMENT_COMMAND = "addComment";
    private static final String DELETE_COMMENT_ITEM_COMMAND = "deleteCommentItem";
    private static final String PERSONAL_PAGE_COMMAND = "personal";
    private static final String REGISTRATION_PAGE_COMMAND = "registrationPage";
    private static final String REGISTRATION_COMMAND = "registration";
    private static final String LOGIN_PAGE_COMMAND = "loginPage";

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
            case SHOPPING_CART_COMMAND:
                return new ShoppingCartCommand(new OrderServiceImpl(new DaoHelperFactory()));
            case ADD_TO_SHOPPING_CART_COMMAND:
                return new AddToShoppingCartCommand(new OrderServiceImpl(new DaoHelperFactory()));
            case DELETE_FROM_SHOPPING_CART_COMMAND:
                return new DeleteFromShoppingCartCommand(new OrderServiceImpl(new DaoHelperFactory()));
            case INCREMENT_FOOD_IN_SHOPPING_CART_COMMAND:
                return new IncrementFoodInShoppingCart(new OrderServiceImpl(new DaoHelperFactory()));
            case DECREMENT_FOOD_IN_SHOPPING_CART_COMMAND:
                return new DecrementFoodInShoppingCart(new OrderServiceImpl(new DaoHelperFactory()));
            case PAY_ORDER_COMMAND:
                return new PayOrderCommand(new OrderServiceImpl(new DaoHelperFactory()));
            case DELETE_FOOD_ITEM_COMMAND:
                return new DeleteFoodItemCommand(new FoodServiceImpl(new DaoHelperFactory()));
            case SAVE_FOOD_ITEM_COMMAND:
                return new SaveFoodItemCommand(new FoodServiceImpl(new DaoHelperFactory()));
            case ADD_FOOD_ITEM_COMMAND:
                return new AddFoodItemCommand(new FoodServiceImpl(new DaoHelperFactory()));
            case USERS_COMMAND:
                return new UsersCommand(new UserServiceImpl(new DaoHelperFactory()));
            case CHANGE_BLOCK_COMMAND:
                return new ChangeBlockCommand(new UserServiceImpl(new DaoHelperFactory()));
            case CHANGE_LOYALTY_POINTS_COMMAND:
                return new ChangeLoyaltyPointsCommand(new UserServiceImpl(new DaoHelperFactory()));
            case ORDERS_COMMAND:
                return new OrdersCommand(new OrderServiceImpl(new DaoHelperFactory()));
            case CHANGE_RATING_COMMAND:
                return new ChangeRatingCommand(new FoodServiceImpl(new DaoHelperFactory()));
            case ORDER_IS_TAKEN_COMMAND:
                return new OrderIsTakenCommand(new OrderServiceImpl(new DaoHelperFactory()), new UserServiceImpl(new DaoHelperFactory()));
            case ORDER_IS_REJECTED_COMMAND:
                return new OrderIsRejectedCommand(new OrderServiceImpl(new DaoHelperFactory()), new UserServiceImpl(new DaoHelperFactory()));
            case RATING_COMMAND:
                return new RatingCommand(new FoodServiceImpl(new DaoHelperFactory()));
            case ADD_COMMENT_COMMAND:
                return new AddCommentCommand(new FoodServiceImpl(new DaoHelperFactory()));
            case DELETE_COMMENT_ITEM_COMMAND:
                return new DeleteCommentItemCommand(new FoodServiceImpl(new DaoHelperFactory()));
            case PERSONAL_PAGE_COMMAND:
                return new PersonalPageCommand();
            case REGISTRATION_PAGE_COMMAND:
                return new RegistrationPageCommand();
            case REGISTRATION_COMMAND:
                return new RegistrationCommand(new UserServiceImpl(new DaoHelperFactory()));
            case LOGIN_PAGE_COMMAND:
                return new LoginPageCommand();
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
