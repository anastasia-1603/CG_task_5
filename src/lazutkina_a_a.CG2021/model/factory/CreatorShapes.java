package model.factory;

import model.ModelType;

public class CreatorShapes {
    public static ModelFactory createModel(ModelType type)
    {
        ModelFactory factory = null;
        switch (type)
        {
            case OVAL -> factory = new OvalFactory();
            case SQUARE -> factory = new SquareFactory();
            default -> System.out.println("Нет таких моделей");
        }
        return factory;
    }
}
