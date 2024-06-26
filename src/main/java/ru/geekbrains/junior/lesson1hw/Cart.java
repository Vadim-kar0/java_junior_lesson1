package ru.geekbrains.junior.lesson1hw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing(){
        boolean proteins = false;
        boolean fats = false;
        boolean carbohydrates = false;

        for (var food : foodstuffs)
        {
            if (!proteins && food.getProteins())
                proteins = true;
            else
            if (!fats && food.getFats())
                fats = true;
            else
            if (!carbohydrates && food.getCarbohydrates())
                carbohydrates = true;
            if (proteins && fats && carbohydrates)
                break;
        }

        if (proteins && fats && carbohydrates)
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        for (var thing : market.getThings(clazz))
        {
            if (!proteins && thing.getProteins())
            {
                proteins = true;
                foodstuffs.add(thing);
            }
            else if (!fats && thing.getFats())
            {
                fats = true;
                foodstuffs.add(thing);
            }
            else if (!carbohydrates && thing.getCarbohydrates())
            {
                carbohydrates = true;
                foodstuffs.add(thing);
            }
            if (proteins && fats && carbohydrates)
                break;
        }
        if (proteins && fats && carbohydrates)
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");

    }

    public void cardBalancingV2(){
        boolean proteins = foodstuffs.stream().anyMatch(Food::getProteins);
        boolean fats = foodstuffs.stream().anyMatch(Food::getFats);
        boolean carbohydrates = foodstuffs.stream().anyMatch(Food::getCarbohydrates);
        if(proteins && fats && carbohydrates){
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }
        System.out.println("Начинаем балансировать корзину по БЖУ");
        if(!checkMarketByFood()){
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        } else {
            if (!proteins){
                boolean addProteins = foodstuffs.add((T) market.getThings(Food.class).stream()
                        .filter(food -> food.getProteins()).findAny().get());
            }
            if (!fats){
                boolean addFats = foodstuffs.add((T) market.getThings(Food.class).stream()
                        .filter(food -> food.getFats()).findAny().get());
            }
            if (!carbohydrates){
                boolean addFats = foodstuffs.add((T) market.getThings(Food.class).stream()
                        .filter(food -> food.getCarbohydrates()).findAny().get());
            }
            System.out.println("Корзина сбалансирована по БЖУ.");
        }



    }

    private boolean checkMarketByFood(){
        boolean proteins = market.getThings(Food.class).stream().anyMatch(Food::getProteins);
        boolean fats = market.getThings(Food.class).stream().anyMatch(Food::getFats);
        boolean carbohydrates = market.getThings(Food.class).stream().anyMatch(Food::getCarbohydrates);
        return proteins && fats && carbohydrates;
    }



    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs()
    {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));

    }


}
