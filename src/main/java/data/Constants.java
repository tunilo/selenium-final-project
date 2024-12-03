package data;

import com.github.javafaker.Faker;

public class Constants {
    static Faker faker = new Faker();
    // Locators
    public static final String SWOOP_HOME_URL = "https://www.swoop.ge/";

    // Menu Locators
    public static final String HOLIDAY_PAGE_LINK = "//p[contains(text(), 'დასვენება')]";
    public static final String SORT_BUTTON = "//p[contains(text(), 'შესაბამისობით')]";
    public static final String DESCENDING_ORDER = "//p[contains(text(), 'ფასით კლებადი')]";
    public static final String ASCENDING_ORDER = "//p[contains(text(), 'ფასით ზრდადი')]";
    public static final String PERCENT_SORT = "//p[contains(text(), 'პროცენტით ზრდადი')]";
    public static final String MOUNTAIN_RESORTS = "//h5[contains(text(),'მთის კურორტები')]";
    public static final String CATEGORIES = "//p[contains(text(), 'კატეგორიები')]";
    public static final String KARTING = "//h4[contains(text(), 'კარტინგი')]";
    public static final String SPORT = "//h4[contains(text(), 'სპორტი')]";

    // Filters
    public static final String FULL_PAYMENT_OPTION = "//span[contains(text(),'სრული გადახდა')]";
    public static final String FULL_PAYMENT_ICON = "//p[text()='სრული გადახდა']";

    // Price Locators
    public static final String PRICE_LOCATOR = "//h4[contains(., '₾') and @weight='bold']";
    public static final String DISCOUNT_PRICE_LOCATOR = "//p[contains(text(),'%') and @weight='bold']";

    // Pagination
    public static final String PAGE_BUTTON = "//div[text()='%s']";
    public static final String expectedURL = "https://www.swoop.ge/category/2058/sporti/kartingi/";


    public static String email_input = "tuna&gmail.com";
    public static String password_input = "TeklaForTBC2024";

    public static String firstName_input = faker.name().firstName();
    public static String lastName_input = faker.name().lastName();
    public static String PHONE_INDEX = "599";
    public static String phoneNumber_input = PHONE_INDEX + faker.number().digits(6);
    public static String yearOfBirth_input = String.valueOf(faker.number().numberBetween(1950, 2005));
    public static String phoneCode_input = faker.number().digits(4);
    public static String EMAIL = "email";
    public static String PASSWORD = "password";
    public static String PASSWORD_RETYPE = "PasswordRetype";
    public static String PHONE_CODE = "PhoneCode";
    public static String FIRSTNAME = "firstname";
    public static String LASTNAME = "lastname";
    public static String BIRTH_YEAR = "//span[contains(@class, 'select2-selection--single')]";
    public static String PHONE = "phone";
    public static String CINEMA = "//p[contains(text(), 'კინო')]";
    public static String EAST_POINT = "//h3[contains(text(), 'კავეა ისთ ფოინთი')]";
    public static String LAST_OPTION = "//h3[contains(text(), 'კავეა ისთ ფოინთი')]/ancestor::div[contains(@class, 'flex')]/descendant::div[contains(@class, 'cursor-pointer')][last()]";
    public static String FREE_PLACE = "//*[local-name()='svg' and .//*[local-name()='path' and @fill='#8ECC69']]";
    public static String NEW_ACCOUNT = "//a[contains(text(), 'შექმენი')]";
    public static String GENDER = "//label[@for='Gender2']";
    public static String RULES_CHECKMARK = "//label[input[@id='test']]//span[@class='checkmark']";
    public static String TBC_TERMS = "//label[input[@name='AgreeTBCTerms']]//span[@class='checkmark']";
    public static String MAIL_ERROR = "//p[contains(text(), 'ჩაწერე ელფოსტა')]";
    public static String  MIN_PRICE = "300";
    public static String  MAX_PRICE = "600";
    public static String MIN_PRICE_INPUT = "//div[contains(@class, 'relative') and ./p[contains(text(), 'დან')]]//input\n";
    public static String MAX_PRICE_INPUT = "//div[contains(@class, 'relative') and ./p[contains(text(), 'მდე')]]//input\n";
    public static String FILTER_BUTTON ="//button[contains(@class, 'flex group items-center justify-center') and contains(@class, 'hover:bg-primary_green-20-value')]";
    public static int  MIN_PRICE_INT = 300;
    public static int  MAX_PRICE_INT = 600;
    public static String Kartingi = "კარტინგი";
    public static String CATEGORY_CHAIN_ELEMENTS = ".//a[contains(@data-testid, 'breadcrumb-link')]";
    public static String CATEGORY_CHAIN = "//nav[contains(@class, 'flex-nowrap whitespace-nowrap')]";
    public static String  LOGO = "//img[@alt='swoop']\n";
    public static String movieName = "//h1[contains(@class, 'text-xl') and contains(@class, 'text-white')]\n";
    public static String time = ".//p[contains(@class, 'text-lg')]";
    public static String date = ".//p[contains(@class, 'leading-5')]";
    public static String POPUP_MOVIE = "h2.text-primary_black-100-value.text-md.leading-5.font-tbcx-bold";
    public static String POPUP_DATE_TIME = "//p[contains(@class, 'text-2sm') and contains(@class, 'text-primary_black-90-value') and contains(text(), ':')]";
    public static String FREE_SEAT_COLOR = "//div[contains(@class, 'w-2.5 h-2.5 rounded-full bg-primary_green-100-value')]";

}
