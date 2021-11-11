package com.example.project6_fx;

public interface GameViewInterface {
    /**
     * Render each square that is being passed in to the function
     *  - Get square information
     *      1. Outside/inside
     *          - If outside: don't need to do 2 and 3
     *          - Else
     *              2. Walls
     *              3. Render the object that is on the square
     *                  - FF, smoke, fire, POI
     *  - The argument is a square because Map is just made up with a bunch of squares
     */
    void drawUpdateSquare(SimpleSquare square);

    /**
     * Display available menu options when user clicks the square with an action menu
     * For simplicity: Right now ActionMenu will show all available actions
     *  TODO: Where should the logic of graying out the options be?
     *      - It could be in the controller where you check if an action is legit and gets updated in ActionMenu
     *      - Or if we want to combine it into the viewer
     *   Personally I think it makes more sense if we dealt the logic in the controller for Game and gets
     *   rendered ActionMenu
     */
    void ActionMenu();
}
