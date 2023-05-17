package Game;

import Battle.Item;
import Exploration.NPC;

/**
 * A class for creating cutscenes that help explain the story.
 * @author Ian King
 */
public class Cutscene 
{   
    /**
     * Calls MainGame.printWithBreaks().
     * @param cutscene 
     */
    private static void print(String cutscene)
    {
        MainGame.printWithBreaks(cutscene);
    }
    
    /**
     * If an NPC is to play a cutscene, this method takes the Object reference and starts the appropriate cutscene based on it. 
     * @param person
     */
    public static void startNPCCutscene(NPC person)
    {
        switch(person.getName())
        {
            case "Merda":
                merdaCutscene();
                break;
            case "Fleur":
                fleurCutscene();
                break;
            case "Caillou":
                calliouCutscene();
                break;
            case "Calmus":
                calmusCutscene();
                break;
            case "Frigs":
                frigsCutscene();
                break;
            case "Ninlil":
                ninlilCutscene();
                break;
        }
    }
    
    /**
     * The opening cutscene when a new game starts.
     */
    public static void startingCutscene()
    {
        MainGame.printlnlnWait("The year is 2XXX.", 25, 1500);
        MainGame.printWait("The world", 25, 100);
        MainGame.ellipsis();
        MainGame.printlnlnWait("a place of war. A constant place of distress.", 25, 1500);
        MainGame.printlnlnWait("A place of loss. A place where peace is seldom found.", 25, 2000);
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        print("Somewhere, there is a man with power, technology, and money./So much so, that people look up to him. They hope "
                + "that he can somehow fix things./With his capabilities, he has made great advancements and "
                + "improved the world in some ways./However, one thing he cannot do is stop all wars.");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        MainGame.printlnlnWait("After a conference meeting that took place with people buzzing around:", 25, 1000);
        MainGame.dialoguelnln("Newscaster", "And I'm here today trying to ask Irwin Krov about his plans for the future after "
                + "his meeting with goverment officials.");
        MainGame.dialoguelnln("Newscaster", "There he is! Mr. Krov, Mr. Krov! What do you plan to do to help with all the "
                + "wars currently taking place?\n\tWill you be providing tools for the governemnt to use?");
        MainGame.dialoguelnln("Irwin", "What I plan on doing is not to be said to the press.\n\tJust know that in due time, "
                + "a solution will be found. We will be working ever so diligently to bring peace.\n\tYou can depend on that. "
                + "The imperfections we as humans have is what caused the world to fall to ruin.\n\tThe way to combat this is "
                + "perfection.");
        MainGame.dialogueInteract("Newscaster", "... Th-thank you, Mr. Krov... Well, you heard it here first. Will our world be "
                + "restored?\n\tWill we have peace once - or ever - again? Will the supposed 'perfection' needed "
                + "help us?\n\tI'm Natasha Green with 10PM News, signing off.");
        //-----------------------------------------------------------------------------------------------------------------------
        
        MainGame.ellipsis();
        MainGame.wait(1000);
        MainGame.clearScreen();
        
        print("Amidst the blue terror called the ocean, there is a special island./On this island, there is a special people with "
                + "the ability to control the elements./Using these powers, the people have kept themselves hidden from the rest "
                + "of the world for their safety./With some having the ability to control water, wind, and electricity, "
                + "the island is surrounded by violent storms,/preventing anyone and anything from getting through.");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        print("Despite the violent storms, the people are extremely tranquil./They all live in harmony, helping others when "
                + "needed and ensuring that life together is peaceful.");
        print("This island is called Pulchra. It's full of beauty, vast creatures, and a substance called ethrellium./"
                + "This substance is only found on the island and is the source of the elemental powers./It's found in the "
                + "vegetation and wildlife, allowing the creatures to have powers themselves.");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        print("A bright, young girl named Anahita is found at Purity Beach, located to the south of the island./She's "
                + "peacefully absorbing the beach's scenery and enjoying the gentle breeze./She realizes that she let the "
                + "time slip past her, however, and that she's close to being late.");
        MainGame.dialoguelnln("Anahita", "*sigh* I wish I could stay here forever...\n\t"
                + "Oh no! I forgot I need to get back home to help Mom prepare for the annual festival!\n\tYou've gone and done it "
                + "now, Ana... Ugh!\n\tOkay, okay, I'll be fine. To get back home, I just have to go through Opicon Forest...");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene for when the player first enters Opicon Forest.
     */
    public static void opiconCutscene()
    {
        MainGame.clearScreen();
        
        MainGame.dialoguelnln("Anahita", "Wow. This place will always take my breath away. All the flowers and vegetation are "
                + "amazing!");
        MainGame.dialoguelnln("Anahita", "Ah, I keep getting distracted! Focus, Ana, focus!");
        MainGame.dialoguelnln("Anahita", "Okay I've got this. The enemies here are stronger than the ones at the beach, so I "
                + "need to be mindful...");
        MainGame.dialoguelnln("Strange Voice 1", "Anahita, is that you?");
        
        MainGame.printlnlnWait("Anahita screamed in terror by the sudden voice and used her Tsunami Shot attack where the "
                + "voice came from.\nShe then cowered and held her fingers out for another blind shot.", 25, 1500);
        MainGame.dialoguelnln("Strange Voice 1", "Ow! Was that necess- *gasp* my *hair*!");
        MainGame.dialoguelnln("Strange Voice 2", "Haha! It's a step up from what it was before.");
        MainGame.dialoguelnln("Strange Voice 1", "Oh shut it, Fultra. I spent so long on it today for the festival...");
        MainGame.printlnlnWait("Anahita realized who the voices belonged to: her best friend Gaea and her boyfriend, Fultra.", 25, 1500);
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        MainGame.printlnlnWait("She ran up to them, her hands covering her mouth and a guilty expression.", 25, 1500);
        MainGame.dialoguelnln("Anahita", "Oh my gosh! I'm so sorry Gaea. Are you okay?");
        MainGame.dialoguelnln("Gaea", "*sniff* I'll be fine... I guess...");
        MainGame.dialoguelnln("Fultra", "You'll know she'll be okay, Ana. She's more concerned about her hair than anything else.");
        MainGame.dialoguelnln("Gaea", "Don't down play it like it's nothing! I have to redo it all before the festival now!");
        MainGame.dialoguelnln("Anahita", "I promise I'll help you clean up a bit, okay? What're you guys doing here though?");
        MainGame.dialoguelnln("Gaea", "We were picking flowers to give to my cousin, Fleur. We have 4 baskets ready to go!");
        MainGame.dialoguelnln("Fultra", "And we thought we heard your voice, so we decided to see if it was you.\n\tAnd it "
                + "clearly was considering the damage done.");
        MainGame.dialoguelnln("Gaea", "It's fine, it's fine! Let's just move on. What're you doing, Ana?");
        MainGame.dialoguelnln("Anahita", "I was at Purity Beach by myself for a while when I lost track of time. I need to get\n\t"
                + "back home to see if my family needs help with preparations.");
        MainGame.dialoguelnln("Gaea", "Why don't you let us accompany you? We can stop by the Earth Village afterwards anyway.");
        MainGame.dialoguelnln("Fultra", "And maybe we'll prevent another bad hair day for someone else...");
        MainGame.printlnlnWait("Gaea then playfully punched Fultra's arm, making Anahita laugh a little.", 25, 1000);
        MainGame.dialoguelnln("Anahita", "I'd love if you guys could help me! Thank you so much.");
        MainGame.dialoguelnln("Fultra", "Not a problem. You have 'Fearless Thunder' with you, so there's nothing to fear!");
        MainGame.dialoguelnln("Gaea", "*sigh* There he goes again, boosting that ego. Come on, Ana. Let's go!");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        MainGame.printlnlnWait("Congratulations! Gaea and Fultra joined your team!", 25, 150);
        
        MainGame.promptToEnter();
        
        MainGame.printWithBreaks("You now have access to Fultra, one of the strongest Pulchrians!/He has a unique class called the 'All-Rounder.'/"
                + "All his stats are average and realatively even, but what really makes him shine is his Buff Attack called 'Charge!'/"
                + "With it, he increases all his stats for a certain time, but it has a large cooldown, so be mindful!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene right after forest tutorial.
     */
    public static void opiconCutscene2()
    {
        MainGame.clearScreen();
        
        MainGame.printlnlnWait("The three friends let out a collective sigh after defeating the enemies and gave each other "
                + "high fives.", 25, 1500);
        
        MainGame.dialoguelnln("Fultra", "Phew! That Krobble seemed very hostile. We handled it quite well though!");
        MainGame.dialoguelnln("Gaea", "Yeah! Nice healing back there, Ana!");
        MainGame.dialoguelnln("Anahita", "Thanks! And thank you guys for helping me.\n\tI don't think I would've been able to "
                + "handle them all on my own.");
        MainGame.dialoguelnln("Gaea", "But of course! We wouldn't just let you *die* here, right Fultra?");
        MainGame.dialoguelnln("Fultra", "... r-right... yeah...");
        
        MainGame.printlnlnWait("Gaea punched Fultra on the arm again.", 25, 1500);
        
        MainGame.dialoguelnln("Gaea", "That's not cool Fultra!");
        MainGame.dialoguelnln("Fultra", "Haha, you know I'm kidding! Jokes aside, we're happy to help you anytime, Ana.\n\t"
                + "Let's move on to the Water Village then, shall we?");
        MainGame.dialoguelnln("Gaea and Anahita", "Sounds good!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene when first entering the Water Village.
     */
    public static void waterVillageCutscene()
    {
        MainGame.clearScreen();
        
        MainGame.printlnlnWait("Anahita and the others safely arrived at the Water Village, Anahita's home.", 25, 1500);
        
        MainGame.dialoguelnln("Anahita", "Awesome - we made it! Thank you guys so much for accompanying me!\n\t"
                + "I need to check on my family first, and then we can go to the Earth Village.");
        MainGame.dialoguelnln("Gaea", "Okay! Sounds like a plan. I'd like to see your mom again anyway.\n\tDo you think she'll "
                + "have Cinnamon Rolls?");
        
        print("Gaea said this with a big grin on her face.");
        print("Ever since they were kids, Anahita and Gaea have had a sweet tooth./They would occasionally steal Cinnamon Rolls "
                + "and other desserts from Anahita's mother and run into the forest to eat./They've since grown out of this, "
                + "thankfully...");
        
        MainGame.dialoguelnln("Anahita", "She might! I beleive that's what she plans on making for the festival.");
        print("Fultra shook his head and put a hand on his face.");
        MainGame.dialoguelnln("Fultra", "You guys just can't resist the sweets, huh?");
        MainGame.dialoguelnln("Gaea", "Not at all. Desserts are the best thing ever.");
        
        print("Gaea said this matter-of-factly while raising a single finger with her eyes closed.");
        
        MainGame.dialoguelnln("Anahita", "You simply have bad tastes if you don't enjoy them, Fultra.");
        print("Anahita shrugged her shoulders as she teased him, but all Fultra did was laugh.");
        MainGame.dialoguelnln("Fultra", "You two are something else when you're together.");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene for when talking to Merda for the first time.
     */
    public static void merdaCutscene()
    {
        MainGame.dialoguelnln("Merda", "Oh! There you are. Hello everyone.");
        MainGame.dialoguelnln("Anahita", "Hey, Mom! I'm so so sorry that I got here late. I lost track of time and I-");
        MainGame.dialoguelnln("Merda", "It's okay, Ana. Brinlee and I already took care of the baking.");
        MainGame.dialoguelnln("Anahita", "Oh, okay... Again, I'm really sorry, Mom... Is there anything else you might need help "
                + "with?");
        MainGame.dialoguelnln("Merda", "No, I think we covered everything.\n\tI thought you would start feeling guilty after "
                + "I saw you were late, so Brinlee and I made a few extra just in case.");
        print("Both Anahita and Gaea's faces lit up when Merda brought a plate of extra Cinnamon Rolls for them./They beemed "
                + "with glee and Fultra couldn't help but smile at their energy.");
        MainGame.dialoguelnln("Gaea", "Awesome! Merda, you're amazing! Oh, and they smell even better!");
        print("Merda smiled and laughed as the girls indulged in the treats.");
        MainGame.dialoguelnln("Merda", "Fultra, would you care for one?");
        MainGame.dialoguelnln("Fultra", "Thank you, but I think I'll have to pass for now.");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        MainGame.dialoguelnln("Anahita", "Whaf duh yah mean you dun whan any?");
        MainGame.dialoguelnln("Gaea", "Yah'r miffin ouh. Try suhm!");
        print("Merda and Fultra were both shocked at how quickly the girls stuffed their mouths.");
        MainGame.dialoguelnln("Fultra", "I'm sorry, but you'll have to repeat that for me.");
        print("The girls quickly finished and repeated themselves.");
        MainGame.dialoguelnln("Anahita", "What do you mean you don't want any? I thought what I said was obvious.");
        MainGame.dialoguelnln("Gaea", "And I said, 'You're missing out. Try some!' They're amazing!");
        print("The two were practically beaming with joy, and any guilt Anahita felt for being late evaporated.");
        MainGame.dialoguelnln("Fultra", "Alright, you've convinced me.");
        print("Fultra took one of the Cinnamon Rolls and ate it. After the first bite, he couldn't help but finish the rest/"
                + "as quickly as the girls. He then started beaming too.");
        MainGame.dialoguelnln("Fultra", "Wow! Merda, these are amazing!");
        print("Anahita and Gaea gave each other a look and nodded as if they were communicating telepathically.");
        MainGame.dialoguelnln("Merda", "Well, thank you, Fultra. You all clearly seemed to have enjoyed them. Hopefully those\n\t"
                + "at the festival do too.");
        MainGame.dialoguelnln("Merda", "Brinlee and I will be leaving soon for the annual festival. I'll give you guys some "
                + "extras before we go though.");
        MainGame.dialoguelnln("Anahita", "Oh, wonderful! Thank you so much. You're the best.");
        print("Anahita gave Merda a hug before giving the others her attention.");
        MainGame.dialoguelnln("Anahita", "Alright! Off to the Earth Village then, right?");
        MainGame.dialoguelnln("Gaea", "Yup! I'm ready when you guys are.");
        MainGame.dialoguelnln("Fultra", "Let's go then!");
        
        MainGame.promptToEnter();
        
        MainGame.printlnln("You received 3 Cinnamon Rolls!", 25);
        MainGame.addToInventory(Item.getHealingItem("Cinnamon Roll"), 3);
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscecne for when the player first enters the Earth Village.
     */
    public static void earthVillageCutscene()
    {
        MainGame.dialoguelnln("Gaea", "Aaaand we made it! Alright, this is our last stop before the annual festival, and then\n\t"
                + "we can party!");
        MainGame.dialoguelnln("Fultra", "I've been waiting all day! It'll be nice to catch up with some people from the other "
                + "villages.");
        MainGame.dialoguelnln("Anahita", "Oh, maybe Frigs and Calmus will be there, too!");
        MainGame.dialoguelnln("Gaea", "We haven't seen either of them in a long while, have we? Well, let's deliver these "
                + "flowers to Fleur first.\n\tThen, you need to live up to your promise and fix my hair, Ana!");
        print("Gaea's hair was no longer wet, but it was far from pleasant to look at. Anahita winced sightly as she\n"
                + "had breifly forgotten.");
        MainGame.dialoguelnln("Anahita", "Right, right! I'll braid it nicely for you. Let's find Fleur first.");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene for when talking to Fleur for the first time.
     */
    public static void fleurCutscene()
    {
        print("Fleur is found talking about decorations for the festival to a team of other Pulchrians.");
        MainGame.dialoguelnln("Fleur", "... Perfect! All we need now are the baskets of flowers. Where is Gaea?\n\t"
                + "Right when I need her most, she's no where to be found...");
        MainGame.dialoguelnln("Gaea", "They're right here!");
        print("The trio ran to Fleur with the 4 baskets of flowers they picked from Opicon Forest.");
        MainGame.dialoguelnln("Fleur", "Oh, wonderful! I was wondering where you- oh my. What happened to your hair?");
        print("The group explained everything that happened since Anahita was found in the forest.");
        MainGame.dialoguelnln("Fleur", "I see - so that's why you're late. Well, we have the flowers now, and that's all that "
                + "matters!");
        MainGame.dialoguelnln("Anahita", "We're so sorry for getting them here late, but I'm sure they'll look amazing for the "
                + "festival!");
        MainGame.dialoguelnln("Fleur", "I hope so. I've had the decorations in mind for the past month! I'm on a tight schedule "
                + "now,\n\tbut I'll see you all there!");
        print("Fleur and the others she was previously talking to gather the delivered flowers and start preparing the last "
                + "decorations.");
        MainGame.dialoguelnln("Gaea", "Awesome! Thank you guys for your pateince. Ana, if we could fix my hair before we go,\n\t"
                + "that would be great.");
        MainGame.dialoguelnln("Anahita", "Yup! You have a lot of hair, but it hopefully shouldn't take that long.");
        MainGame.dialoguelnln("Fultra", "You guys go ahead. I'll help Fleur and the others with the decorations!");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        print("Anahita and Gaea split from Fultra and went to Gaea's home. Anahita quickly cleaned Gaea's hair and they started "
                + "talking for a while.");
        
        MainGame.dialoguelnln("Gaea", "Y'know, I appreciate you guys.");
        
        print("Anahita was braiding braiding Gaea's hair by this point and stopped breifly.");
        
        MainGame.dialoguelnln("Anahita", "We appreciate you too! I'm not complaining, but why the sentiment?");
        MainGame.dialoguelnln("Gaea", "I feel like I don't say it enough. You and Fultra that mean the world to me. I really "
                + "don't\n\tknow what I would do without either of you.");
        MainGame.dialoguelnln("Anahita", "Aww, Gaea...");
        MainGame.dialoguelnln("Gaea", "And I know I punch on him a lot, but that's just because we mess around a lot. I really "
                + "do care for him.\n\tI hope he knows that.");
        MainGame.dialoguelnln("Anahita", "I'm sure he does. It doesn't hurt to say it still though. Maybe you can talk to him "
                + "during the festival.");
        MainGame.dialoguelnln("Gaea", "Y'know what? You're right. I'll talk to him tonight. See - this is why I appreciate you.");
        print("Anahita laughed as she placed a few flowers in Gaea's hair.");
        MainGame.dialoguelnln("Anahita", "Done! I hope you like it.");
        
        print("Gaea looked in a mirror and admired Anahita's handiwork. Her hair was now neatly braided with flowers in the "
                + "front.");
        
        MainGame.dialoguelnln("Gaea", "Oh, Ana, it's amazing! Thank you so much! It's much better than anything *I've* done!\n\t"
                + "I didn't think that was possible!");
        MainGame.dialoguelnln("Anahita", "What can I say - I'm a girl with many talents.");
        
        //-----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("The two laughed and quickly left Gaea's home. They caught up with Fultra right as Fleur's decoration team left to "
                + "finish the last\n\tdecorations at Zoni Village.");
        
        MainGame.dialoguelnln("Fultra", "Wow! You look amazing!");
        MainGame.dialoguelnln("Gaea", "You think so? Ana is to thank. She did an amazing job.");
        MainGame.dialoguelnln("Fleur", "Thank you Ana! It's definitely a lot better. If you didn't, the whole island would've "
                + "felt the 'Wrath of Gaea'.");
        
        print("Everyone but Gaea laughed at Fleur's remark. Gaea huffed in retaliation.");
        
        MainGame.dialoguelnln("Fleur", "I'm just kidding! Jokes aside, thank you as well for your help, Fultra.");
        MainGame.dialoguelnln("Fultra", "It's not a problem! I'm always willing to help.");
        
        print("Fleur smiled before walking away from the group.");
        
        MainGame.dialoguelnln("Anahita", "Alright! It's off to Zoni then, right?");
        MainGame.dialoguelnln("Gaea", "Yes! Oh, it'll be wonderful!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene for when talking to Calliou for the first time (he explains beans and a bit of ethrellium).
     */
    public static void calliouCutscene()
    {
        MainGame.dialoguelnln("Calliou", "Y O U  T H R E E!!!");
        
        print("Anahita, Gaea, and Fultra all flinched at the sheer power coming from his voice.");
        
        MainGame.dialoguelnln("Fultra", "Y-yes sir?");
        MainGame.dialoguelnln("Calliou", "DO YOU KNOW HOW PRECIOUS BEANS ARE??");
        
        print("Calliou showed the group a handful of colorful beans that glistened slightly.");
        
        MainGame.dialoguelnln("Calliou", "BEANS ARE AMAZING!! THEY'RE CHOCK FULL OF ETHRELLIUM, THE SOURCE OF YOUR POWERS!!!");
        MainGame.dialoguelnln("Calliou", "EATING THEM MAKES YOU STRONGER!!! AFTER EATING ONE, THE EFFECTS DON'T "
                + "DISSIPATE UNTIL A BATTLE IS OVER.\n\tIT'S WONDERFUL!!!");
        MainGame.dialoguelnln("Anahita", "Wow, I didn't know that!");
        MainGame.dialoguelnln("Calliou", "NOW YOU KNOW!! HERE - TAKE SOME!!! BEANS ARE AMAZING!!!");
        
        MainGame.printlnln("You received 3 Orange Beans!", 25);
        MainGame.addToInventory(Item.getBuffItem("Orange Bean"), 3);
        
        MainGame.dialoguelnln("Calliou", "REMEMBER THIS IF YOU'RE IN A TOUGH SITUATION - 'BEANS MAKE YOU STRONGER FOR LONGER"
                + "!!!'");
        MainGame.dialoguelnln("Calliou", "W O O O O O ! ! !");
        
        print("Anahita and the others accepted the beans and backed away slowly from Calliou, who was now flexing because of the "
                + "beans' power./");
        
        MainGame.dialoguelnln("Gaea", "I learned something useful... but I am terrified of him...");
       
        print("Anahita nodded slowly in agreement, her eyes wide in fear.");
        
        MainGame.dialoguelnln("Fultra", "H-he seems kind, a-at least. If he were to attack, 'F-fearless Thunder' would protect "
                + "y-you!");
        MainGame.dialoguelnln("Gaea", "Fultra, why is your voice shaking?");
        MainGame.dialoguelnln("Fultra", "N-no reason...!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene for when the player first enters the Earth Village.
     */
    public static void zoniVillageCutscene()
    {
        MainGame.dialoguelnln("Anahita", "Finally! We made it!!");
        
        print("Anahita and the others rush into Zoni Village and admire the animated area. People were going back and forth, "
                + "helping with last minute/decorations and setting up food. The group saw Fleur, busy guiding people to set "
                + "up the flowers Gaea and Fultra picked earlier that day.");
        
        print("The group stared in amazement at the beauty of the village. Gaea then started to sniff the air, a sweet aroma "
                + "captivating her.");
        
        MainGame.dialoguelnln("Gaea", "*sniff sniff* I can smell... Cinnamon Rolls... Triple Chocolate Meltdown... Apple "
                + "Pies... Oh, today is gonna be amazing!\n\tThere's desserts galore!");
        
        print("Fultra laughed at the enthusiasm of Gaea's sleuthing nose and attitude.");
        
        MainGame.dialoguelnln("Fultra", "You two will have a field day with the food. Don't forget the annual dance though! "
                + "The music, the food...\n\teverything will be wonderful.");
        
        MainGame.dialoguelnln("Anahita", "I agree! This is always the best time of the year. Before we do anything else, we "
                + "should talk to everyone to see if\n\tanyone needs help. I also see some familar faces, so let's go say hi!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene for when the player talks to Calmus at Zoni Village.
     */
    public static void calmusCutscene()
    {
        print("The group approaches a young male playing with what appears to be his little sister. He's quite tall and "
                + "towers over the group. Anahita/calls out to him.");
        
        MainGame.dialoguelnln("Anahita", "Hey, Calmus? Is that you?");
        
        print("The young man lets his sister leave and turns around. He flashes a bright smile once he sees the group.");
        
        MainGame.dialoguelnln("Calmus", "Ah, Anahita! Fultra and Gaea! How are you guys doing? It's been AGES!");
        
        print("Calmus gives the group a hug, smiling at them all. Despite his size, he's known to be a sort of gentle giant. "
                + "His strength and endurance/is admired by many. He's known the group for years, but the journey to the Fire "
                + "Village is too strenous for frequent visits. Even though/he's physicially strong, his emotions "
                + "is something he fights with frequently.");
        
        MainGame.dialoguelnln("Fultra", "It's been too long! How's your family doing?");
        
        MainGame.dialoguelnln("Calmus", "I'm just taking care of them as normal and helping when I can. "
                + "Despite everything, we're still holding strong. But how are you guys?");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Gaea", "We're doing quite well! We're excited to eat some wonderful food, listen to the "
                + "music - everything! You know\n\twe love this time of the year.");
        
        MainGame.dialoguelnln("Calmus", "I don't blame you. It's amazing to watch all of Pulchra come together in harmony... "
                + "It's beautiful.\n\tI should let you guys go though! Go enjoy the festival, but I'd like to catch up before "
                + "you guys leave.");
        
        MainGame.dialoguelnln("Gaea", "We definitely should! We'll see you again soon, Calmus!");
        
        MainGame.dialoguelnln("Calmus", "And before you guys leave, take this! My grandmother made extra.");
        
        MainGame.promptToEnter();
        
        MainGame.printlnln("You received an Apple Pie!", 25);
        MainGame.addToInventory(Item.getHealingItem("Apple Pie"), 1);
        
        MainGame.promptToEnter();
        
        print("Anahita and Gaea smell the wonderful scent from the pie.");
        
        MainGame.dialoguelnln("Anahtia", "Thank you so much! We'll see you soon, Calmus! Enjoy the festival!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene for when the player talks to Frigs  at Zoni Village.
     */
    public static void frigsCutscene()
    {
        MainGame.dialoguelnln("Anahita", "Hey, I think I see Frigs over here! I haven't seen him in so long!");
        
        MainGame.dialoguelnln("Gaea", "It might be! Let's go say hi!");
        
        print("The group approaches Frigs, a thin-looking young man. He was watching people put the decorations up as he "
                + "played with a snowflake/on his finger. Anahita snuck up behind him and poked him with her fingers ready "
                + "to use Tsunami Shot.");

        MainGame.dialoguelnln("Anahita", "Boo!");
        
        print("Frigs let out a cry that was uncharacteristic for someone that's known to be nonchalant. He pushed a laughing "
                + "Anahita/away as he recollected himself. Gaea and Fultra were laughing too.");
        
        MainGame.dialoguelnln("Frigs", "Oh my gosh... Ana! Can you not do that?");

        print("Frigs tried to be angry but couldn't help but laugh with the group.");
        
        print("Frigs, a normally witty, yet collected person is another childhood friend of Anahita's. They couldn't visit "
                + "each other often, so they/would talk long into the night after annual festivals.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Frigs", "I can't be too mad. It's wonderful to see you all.");
        
        MainGame.dialoguelnln("Anahita", "It's great to see you too! Are you excited for the festival?");
        
        MainGame.dialoguelnln("Frigs", "Of course! I wouldn't miss it for the world. Especially our annual catch-up "
                + "conversations.");
        
        print("Gaea and Fultra both gave each other a suggestive look but stayed quiet.");
        
        MainGame.dialoguelnln("Anahita", "I'm glad to hear it! We want to see who else we can find, but we'll catch up soon. "
                + "See you later!");
        
        print("The group and Frigs exchanged hugs and smiles.");
        
        MainGame.dialoguelnln("Frigs", "See you soon!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene for when the player talks to Ninlil  at Zoni Village.
     */
    public static void ninlilCutscene()
    {
        print("Anahita was walking and talking to Gaea and Fultra, sharing her enthusiasm with the group. Her excitement, "
                + "unfortunately, blinded her/and caused her to bump into someone helping with decorations. A large basket of "
                + "flowers and streamers fell on the ground.");
        
        MainGame.dialoguelnln("Anahita", "Oh my gosh! I'm so sorry. Here, let me help yo- Ninlil?");
        
        print("The person Anahita bumped into was Ninlil. She's strong, well-respected, and holds herself to a high esteem. "
                + "Anahita and Gaea seldom/have smooth interactions with her.");        
        
        MainGame.dialoguelnln("Ninlil", "Ugh, now I have to pick all this up...! Oh... it's you lot. Are you "
                + "blind or something? Although, I guess I shouldn't\n\tbe too surprised since its you, Ana...");
        
        print("Ninlil starts to pick up the decorations, waving Anahita's hands away, preventing her from helping.");
        
        print("Ninlil's arrogance causes her to not have many good relationships. The only one she has is with Ilven, "
                + "her training partner.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Gaea", "Ninlil, it was one mistake. Can you not have an attitude for once in your life?!");
        
        print("Ninlil gave Gaea a sharp side eye as she continuted to pick up the mess.");
        
        MainGame.dialoguelnln("Ninlil", "First, I dont't have an attitude, okay? Second, this wouldn't have happened if you "
                + "would watch where you're going.\n\tIt's really not that hard.");
        
        MainGame.dialoguelnln("Gaea", "Why are you always so pretenious? Get off your high horse already!");
        
        MainGame.dialoguelnln("Fultra", "Hey, calm down a bit Gaea. We're sorry, Ninlil. We'll leave you be, and we're sorry "
                + "for bumping into you.");
        
        print("Fultra pulls Gaea and Anahita along to diffuse the conflict. Once the group is out of earshot, Gaea "
                + "releasees pent up anger.");
        
        MainGame.dialoguelnln("Gaea", "Oh, I can't *stand* her! She always thinks she so high and mighty and above everyone "
                + "else. Ugh, I'd hate to\n\thave to do *anything* with her!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene that plays after the player talks to everyone at the Annual Festival.
     */
    public static void warCutscene()
    {
        // Look at notes on phone for how this should progress.
    }
}
