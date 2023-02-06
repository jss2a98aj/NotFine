package jss.util;

public class DirectionHelper {

    public static final Directions[] orderedDirections = {
        Directions.YNeg,
        Directions.YPos,
        Directions.ZNeg,
        Directions.ZPos,
        Directions.XNeg,
        Directions.XPos
    };

    public static final Directions[] oppositeDirections = {
        Directions.YPos,
        Directions.YNeg,
        Directions.ZPos,
        Directions.ZNeg,
        Directions.XPos,
        Directions.XNeg
    };

    public static final int[] yDirectionalIncrease = {
        -1,
        1,
        -0,
        0,
        -0,
        0
    };

    public static final int[] zDirectionalIncrease = {
        -0,
        0,
        -1,
        1,
        -0,
        0
    };

    public static final int[] xDirectionalIncrease = {
        -0,
        0,
        -0,
        0,
        -1,
        1
    };

}
