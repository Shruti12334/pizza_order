class Pizza {
    private int price;
    private Boolean veg;
    private int basePizzaPrice = 300; // Default base price
    private int extraCheesePrice = 100; // Default price for extra cheese
    private int extraToppingsPrice = 100; // Default price for extra toppings
    private int backpack = 20; // Default price for take away
    private boolean isExtraCheeseAdded = false;
    private boolean isExtraToppingsAdded = false;
    private boolean isOptedForTakeAway = false;

    public Pizza(Boolean veg) {
        this.veg = veg;
        if (!this.veg) {
            this.basePizzaPrice += 100; // Non-vegetarian surcharge
        }
        this.price = basePizzaPrice;
    }

    public void addExtraCheese() {
        isExtraCheeseAdded = true;
        this.price += extraCheesePrice;
    }

    public void addExtraToppings() {
        isExtraToppingsAdded = true;
        this.price += extraToppingsPrice;
    }

    public void takeAway() {
        isOptedForTakeAway = true;
        this.price += backpack;
    }

    public void getBill() {
        String bill = "Pizza: " + basePizzaPrice + "\n";
        if (isExtraCheeseAdded) {
            bill += "Extra Cheese Added: " + extraCheesePrice + "\n";
        }
        if (isExtraToppingsAdded) {
            bill += "Extra Toppings Added: " + extraToppingsPrice + "\n";
        }
        if (isOptedForTakeAway) {
            bill += "Take Away: " + backpack + "\n";
        }
        bill += "The Total Bill Is : " + this.price + "\n";
        System.out.println(bill);
    }
}
