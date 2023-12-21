package View;

import Controller.LanguageManager;

public enum Instructions {

    PLACE_BET(LanguageManager.getMessage("placeBet")),
    DECIDE_NEXT_MOVE(LanguageManager.getMessage("makeMove")),
    BUSTED("Du gick över 21. Du har tyvärr förlorat. Klicka på 'New game' för att försöka igen"),
    WON_ROUND("Grattis! Du vann. Klicka på 'New game' för en ny runda"),
    LOST_ROUND("Tyvärr, du förlorade. Klicka på 'New game' för att försöka igen"),
    DRAW_ROUND("Det blev oavgjort. Klicka på 'New game' för en ny runda");

    private final String instruction;

    Instructions(String instruction) {
        this.instruction = instruction;
    }

    public String getInstructionSV() {
        return instruction;
    }
}

