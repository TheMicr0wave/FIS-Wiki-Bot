package bot;

import java.util.HashMap;

public class WikiChecker {

    private final HashMap<String, Integer> triggerValues = new HashMap<>();
    private final HashMap<String, Integer> nonDescriptTrigger = new HashMap<>();

    public WikiChecker() {
        // Hashmap Values ---------------------------------------

        // Special Words  ---------------------------------------
        triggerValues.put("ENDURE", 3);
        triggerValues.put("PREPARE", 3);
        triggerValues.put("INCOMING", 3);
        triggerValues.put("CAP", 2);
        triggerValues.put("MODIFIER", 2);
        triggerValues.put("MOD", 2);
        triggerValues.put("PWM", 2);
        triggerValues.put("SPAWN", 1);
        triggerValues.put("SPAWNING", 2);
        triggerValues.put("SPAWNED", 2);
        triggerValues.put("EVOLVE", 2);
        triggerValues.put("SCAN", 2);
        triggerValues.put("HEALTH", 1);
        triggerValues.put("HP", 1);
        triggerValues.put("DAMAGE", 1);
        triggerValues.put("DEFEND", 1);
        triggerValues.put("ARMOR", 1);
        triggerValues.put("ARMOUR", 1);
        triggerValues.put("INFECT", 1);
        triggerValues.put("INFECTION", 1);
        triggerValues.put("INFESTED", 1);
        triggerValues.put("INFESTATION", 1);
        triggerValues.put("CRAFT", 1);
        triggerValues.put("DEFENSE", 1);
        triggerValues.put("MYCELIUM", 1);
        triggerValues.put("VARIANTS", 2);
        triggerValues.put("VARIANT", 1);
        triggerValues.put("COLD", 1);

        // Mobs & Mob Classes ------------------------------------
        triggerValues.put("CALAMITIES", 2);
        triggerValues.put("CALAMITY", 1);
        triggerValues.put("EVOLVED", 1);
        triggerValues.put("HYPER", 1);
        triggerValues.put("INFECTED", 1);
        triggerValues.put("INF", 1);
        triggerValues.put("DISEASED", 1);
        triggerValues.put("KNIGHT", 1);        // Evolved
        triggerValues.put("GRIEFER" , 1);
        triggerValues.put("BRAIOMIL", 1);
        triggerValues.put("PHAYRES", 1);
        triggerValues.put("VERDATHORN", 1);
        triggerValues.put("JAGDHUND" , 1);
        triggerValues.put("SCAVENGER", 1);
        triggerValues.put("BLOATER", 1);
        triggerValues.put("NAIAD", 1);
        triggerValues.put("LEAPER", 1);
        triggerValues.put("SLASHER", 1);
        triggerValues.put("SPITTER", 1);
        triggerValues.put("VOLATILE", 1);
        triggerValues.put("MEPHITIC", 1);
        triggerValues.put("HOWLER", 1);
        triggerValues.put("STALKER", 1);
        triggerValues.put("BRUTE", 1);
        triggerValues.put("NUCKELAVEE", 1);
        triggerValues.put("PROTECTOR", 1);
        triggerValues.put("CHEMIST" , 1);
        triggerValues.put("INEBRIATER", 1);
        triggerValues.put("INQUISITOR", 1);     // Hyper Evolved
        triggerValues.put("BROTKATZE", 1);
        triggerValues.put("WENDIGO", 1);
        triggerValues.put("OGRE", 1);
        triggerValues.put("AXTWERFER", 1);
        triggerValues.put("HEXENMEISTER", 1);
        triggerValues.put("HEXEN", 1);
        triggerValues.put("PLAGUED", 1);        // Experiments
        triggerValues.put("LACERATOR", 1);
        triggerValues.put("BIOBLOB", 1);
        triggerValues.put("SAUGLING", 1);
        triggerValues.put("BAIRN", 1);          // Unknown(s)
        triggerValues.put("SCAMPER", 1);
        triggerValues.put("GASTGEBER", 1);
        triggerValues.put("SPECTER", 1);
        triggerValues.put("VANGUARD", 1);
        triggerValues.put("HALLUCINATION", 1);
        triggerValues.put("CLAW", 1);
        triggerValues.put("LICKER", 1);
        triggerValues.put("SCENT", 1);
        triggerValues.put("TENDRIL", 1);
        triggerValues.put("PROTO", 1);          // Organoids
        triggerValues.put("HIVEMIND", 1);
        triggerValues.put("MOUND", 1);
        triggerValues.put("MOUNDS", 1);
        triggerValues.put("UMARMER", 1);
        triggerValues.put("USURPER", 1);
        triggerValues.put("VIGIL", 1);
        triggerValues.put("BRAUREI", 1);
        triggerValues.put("VERWAHRUNG", 1);
        triggerValues.put("DELUSIONER", 1);
        triggerValues.put("WOMB", 1);
        triggerValues.put("SIEGER", 1);         // Calamities
        triggerValues.put("HOWITZER", 1);
        triggerValues.put("HOWITZ", 1);
        triggerValues.put("HOHLFRESSER", 1);
        triggerValues.put("HOHL", 1);
        triggerValues.put("GAZENBREACHER", 1);
        triggerValues.put("GAZEN", 1);
        triggerValues.put("GRAKENSENKER", 1);
        triggerValues.put("GRAKEN", 1);
        triggerValues.put("HINDENBURG", 1);
        triggerValues.put("HINDEN", 1);

        // Items, Blocks -----------------------------------------
        triggerValues.put("ZOAHOLIC", 1);
        triggerValues.put("BCU", 1);
        triggerValues.put("CDU", 1);
        triggerValues.put("INCUBATOR", 1);
        triggerValues.put("SURGERY", 1);
        triggerValues.put("BILE", 1);
        triggerValues.put("RECONSTRUCTED", 1);  // Block Entities
        triggerValues.put("RECON", 1);
        triggerValues.put("OUTPOST", 1);
        triggerValues.put("WATCHER", 1);
        triggerValues.put("REMNANTS", 1);
        triggerValues.put("REAGENTS", 2);       // Reagents
        triggerValues.put("REAGENT", 1);
        triggerValues.put("SYMBIOTIC", 1);
        triggerValues.put("VORACIOUS", 1);
        triggerValues.put("CORROSIVE", 1);
        triggerValues.put("CRYOGENIC", 1);
        triggerValues.put("GASTRIC", 1);
        triggerValues.put("SERRATED", 1);
        triggerValues.put("BIOMASS", 1);        // Items
        triggerValues.put("SYRINGE", 1);
        triggerValues.put("SYRINGES", 2);
        triggerValues.put("SAMPLE", 1);
        triggerValues.put("AGENT", 1);
        triggerValues.put("AGENTS", 2);
        triggerValues.put("SCANNER", 1);


        // Armour & Weapons --------------------------------------
        triggerValues.put("MK2", 1);
        triggerValues.put("LIVING", 1);
        triggerValues.put("GAS", 1);
        triggerValues.put("MASK", 1);


        // Effects & Mutations -----------------------------------
        triggerValues.put("MARKER", 1);
        triggerValues.put("CORROSION", 1);
        triggerValues.put("UNEASY", 1);
        triggerValues.put("MADNESS", 1);
        triggerValues.put("SYMBIOSIS", 1);
        triggerValues.put("MUTATIONS", 1);      // Mutations
        triggerValues.put("BERSERK", 1);
        triggerValues.put("CALCIFIED", 1);
        triggerValues.put("ROTTEN", 1);
        triggerValues.put("TOXIC", 1);
        triggerValues.put("VAMPIRIC", 1);
        triggerValues.put("SKELETAL", 1);
        triggerValues.put("CHARRED", 1);
        triggerValues.put("DROWNED", 1);
        triggerValues.put("REINFORCED", 1);


        // Non Spore Hashmap -------------------------------

        nonDescriptTrigger.put("RID", 2);
        nonDescriptTrigger.put("HELP", 2);
        nonDescriptTrigger.put("WOULD", 1);
        nonDescriptTrigger.put("HOW", 1);
        nonDescriptTrigger.put("WHAT", 1);
        nonDescriptTrigger.put("WHERE", 1);
        nonDescriptTrigger.put("WHY", 1);
        nonDescriptTrigger.put("DOES", 1);
        nonDescriptTrigger.put("CAN", 1);
        nonDescriptTrigger.put("IS", 1);
        nonDescriptTrigger.put("TO", 1);
        nonDescriptTrigger.put("MESSAGE", 1);
        nonDescriptTrigger.put("CHAT", 1);
        nonDescriptTrigger.put("AGE", 1);
        nonDescriptTrigger.put("POTION", 1);
        nonDescriptTrigger.put("POTIONS", 1);
        nonDescriptTrigger.put("BREAK", 1);
        nonDescriptTrigger.put("NIGHTMARE", -5);
        nonDescriptTrigger.put("INQUISITION", -5);
        nonDescriptTrigger.put("BUT", -5);
        nonDescriptTrigger.put("WIKI", -5);
        nonDescriptTrigger.put("CONFIG", -5);
        nonDescriptTrigger.put("JORT", -5);
        nonDescriptTrigger.put("JORTS", -5);
        nonDescriptTrigger.put("DISPITE", -3);
        nonDescriptTrigger.put("VIRUS", -2);
        nonDescriptTrigger.put("VIRAL", -2);
        nonDescriptTrigger.put("FIGHT", -2);
    }

    public int isWikiQuestion (String question){

        String[] questionShell =  question.toUpperCase().split("[\\s\\p{Punct}]+");
        String previousWord =  "";
        int length = 0, trigger = 0;
        double questionValue;
        boolean sporeCheck = false;
        boolean nonSporeCheck = false;

        for (String word : questionShell) {
            // Semi - Content aware question parsing
            if (triggerValues.containsKey(word) && !word.equals(previousWord)) {
                trigger += triggerValues.get(word);
                sporeCheck = true;
            } else if (nonDescriptTrigger.containsKey(word) && !word.equals(previousWord)) {
                trigger += nonDescriptTrigger.get(word);
                nonSporeCheck = true;

            }
            previousWord = word;
            length++;
        }

        if (!(sporeCheck && nonSporeCheck)) {
            return -1;
        }

        questionValue = (double) trigger / (double) length;

        if (length > 1 && questionValue > thresholdValue(length)) {
            return 1;
        } else {
            return -1;
        }
    }

    public double thresholdValue(double x) {
        return (3.0 / (x + 1.6) + 0.1);
    }
}
