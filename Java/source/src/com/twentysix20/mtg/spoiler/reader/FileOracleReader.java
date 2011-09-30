/******************************************************************************
* Created on Mar 25, 2006
*/
package com.twentysix20.mtg.spoiler.reader;

import java.io.IOException;

import com.twentysix20.mtg.data.card.BigFurryMonster;
import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.data.card.CardFactory;
import com.twentysix20.mtg.data.card.CardTemplate;
import com.twentysix20.mtg.data.mana.ManaCostFactory;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.util.StringUtil;

public class FileOracleReader extends BaseFileReader {
    private boolean costHasBeenSet = false;

    public FileOracleReader(String fn) throws IOException {
        super(fn);
    }

    public void handleLine(String line) {
        line = cleanString(line);
        if (StringUtil.isEmpty(line)) {
            if ((template != null) && StringUtil.contains(template.getName(),"B.F.M."))
                template = new BigFurryMonster();
            addCurrentEntryToList();
        } else
            handleNonEmptyLine(line);
    }

    private void handleNonEmptyLine(String line) {
        if (template == null) {
            template = new CardTemplate();
            costHasBeenSet = false;
            template.setName(line);
System.out.println(line);
        } else if (line.toUpperCase().matches(ManaCostFactory.VALID_COST_LINE) && !costHasBeenSet) {
            template.setCost(line);
            costHasBeenSet = true;
        } else if (line.matches(Type.VALID_TYPE_LINE) && (template.getRules().length() == 0)) {
            // if rules are in place, then this is probably a FlipCard
            template.setType(line);
        } else if (line.matches(PowerToughness.VALID_PT_LINE) 
                && (template.getRules().length() == 0) 
                && costHasBeenSet) {  
            // if rules are in place, then this is probably a FlipCard
            template.setPT(line);
        } else {
            if (line.equals("Enchant Player")) // handles Charm School & other Un- enchant players
                template.setType("Enchantment - Aura");
            if (!line.matches("\\[[WUBRG]\\]")) // ignore the basic land [X] abilities
                template.addRule(line);
        }
    }

    protected String cleanString(String text) {
        text = text.replaceAll("oSi","(Snow)").replaceAll("o\\([Ww]\\/[Uu]\\)","(W/U)").replaceAll("o\\([Uu]\\/[Rr]\\)","(U/R)")
            .replaceAll("o\\([Ww]\\/[Bb]\\)","(W/B)").replaceAll("o\\([Uu]\\/[Bb]\\)","(U/B)").replaceAll("o\\([Bb]\\/[Rr]\\)","(B/R)")
            .replaceAll("o\\([Bb]\\/[Gg]\\)","(B/G)").replaceAll("o\\([Rr]\\/[Gg]\\)","(R/G)").replaceAll("o\\([Rr]\\/[Ww]\\)","(R/W)")
            .replaceAll("o\\([Gg]\\/[Ww]\\)","(G/W)").replaceAll("o\\([Gg]\\/[Uu]\\)","(G/U)");
        text = text.replaceAll(" T "," T: ").replaceAll("^T ","T: ");
        return super.cleanString(text);
    }

    public Card createEntryFromTemplate() {
        return CardFactory.create(template);
    }
}
