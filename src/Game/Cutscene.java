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
                // If in the first phase, play Fleur's first cutscene. Otherwise, play her cutscene for the second phase.
                if(!Game.getSecondPhase())
                {
                    fleurCutscene();
                }
                else
                {
                    fleurCutscene2();
                }
                break;
            case "Caillou":
                calliouCutscene();
                break;
            case "Elder Vitorem":
                vitoremCutscene();
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
            case "Elder Nu":
                elderNuCutscene();
                break;
            case "Elder Vulca":
                    elderVulcaCutscene();
                    break;
            case "Lyra":
                if(Game.getDefeatedOmega())
                {
                    helpedLyraCutscene();
                }
                break;
            case "Elder Zeno":
                elderZenoCutscene();
            case "Elder Clairdra":
                elderClairdraCutscene();
        }
    }
    
    /**
     * The opening cutscene when a new game starts.
     */
    public static void startingCutscene()
    {
        MainGame.printlnlnWait("The year is 2XXX.", 25, 1000);
        MainGame.printWait("The world is", 25, 100);
        MainGame.ellipsis();
        MainGame.printWait("a place of war. ", 25, 1000);
        MainGame.printWait("A constant place of distress.", 25, 1000);
        MainGame.printlnlnWait("A place of loss. A place where peace is... seldom found.", 25, 1500);
        MainGame.printlnlnWait("Very, very few can say that they *truly* have peace...", 50, 1000);
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        print("This world, however, has someone they look up to. A man.");
        
        print("A man with money. A man with influence. A man with power. A man with intellect.");
        
        print("People look up to him. They believe that *he* can fix the world. All that's wrong, he can find a solution to it, "
                + "surely.");
        
        print("He has accomplished so many things in the past. He's done so much for humanity. Surely, he can save them from "
                + "this pain. This suffering. This nightmare.");
        
//        print("Somewhere, there is a man with power, technology, and money./So much so, that people look up to him. They hope "
//                + "that he can somehow fix things./With his capabilities, he has made great advancements and "
//                + "improved the world in some ways./However, one thing he cannot do is stop all wars.");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        MainGame.printlnlnWait("(After a conference meeting that took place with people buzzing around):", 25, 1000);
        
        MainGame.dialoguelnln("Newscaster", "I'm here to today at the highly anticipated government meeting that hosted "
                + "special guest,\n\tIrwin Krov. He has been discussing and planning ways to save our corrupted world with "
                + "government officials. It's\n\tonly a matter of time before they finish...");
        
        MainGame.ellipsislnln();
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        print("The doors to a large building open. A man in a suit emerges from the threashold. He has a solid build with "
                + "peppered hair./Following him are 3 bodyguards. Once he's outside, cameras flash and people with microphones "
                + "start crowding the door./The bodyguards start to push a few people back to give the man space. The man "
                + "sighs out of annoyance and shakes his head.");
        
        print("The newscaster surprisingly pushes her way past through the crowd and slips past the bodyguards. "
                + "She then finds/herself face to face with the man. She clears her throat before quickly speaking.");
        
        MainGame.dialoguelnln("Newscaster", "Mr. Krov! If I may take a minute of your time, what do you plan to do to help "
                + "with all the wars and\n\tcorruption currently taking place? Will you be providing tools for the "
                + "governemnt to use?\n\tWhat can we expect from you to bring back hope to those that look up to you?");
        
        print("The newscaster prompts him by moving her microphone in front of him. Irwin sighs again before speaking.");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        MainGame.dialoguelnln("Irwin", "What I plan on doing is not to be said to the press. However, know that a solution "
                + "has been found.\n\tWe will be working ever so diligently to bring peace. You can depend on that. The "
                + "imperfections we as humans\n\thave is what caused the world to fall into ruin in the first place. Thus, "
                + "the way to combat this is perfection.\n\tYou will all understand soon.");
        
        print("Perplexed, the newscaster turns to the cameraman that accompanied her to finish her broadcast.");
        
        MainGame.dialogueInteract("Newscaster", "... Th-thank you, Mr. Krov... Well, you heard it here first. Will our world be "
                + "restored?\n\tWill we have peace once -- or ever -- again? Will the supposed 'perfection' needed help us?"
                + "\n\tI'm Natasha Green with 10PM News, signing off.");
        //-----------------------------------------------------------------------------------------------------------------------
        
        MainGame.ellipsis();
        MainGame.wait(1000);
        MainGame.clearScreen();
        
        print("Amidst the blue terror called the ocean, there is a special, small land./Here, there is a special people "
                + "with the ability to control the elements./Using these powers, the people have kept themselves hidden from "
                + "the rest of the world for their own safety./With some having the ability to control water, wind, and "
                + "electricity, the island is surrounded by violent storms,/preventing anyone and anything from getting "
                + "through.");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        print("Despite the violent storms, the people are extremely tranquil./They all live in harmony, helping others when "
                + "needed and ensuring that life together is peaceful.");
        print("This island is called Pulchra. It's full of beauty, vast creatures, and a substance called ethrellium./"
                + "This substance is only found here and is the source of the elemental powers./It's found in the "
                + "vegetation and wildlife, allowing the creatures to have powers themselves.");
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        print("A bright, young girl named Anahita is found at Purity Beach, located to the south of the island./She's "
                + "peacefully absorbing the beach's scenery and enjoying the gentle breeze./She realizes that she let the "
                + "time slip past her, however, and that she's close to being late.");
        MainGame.dialoguelnln("Anahita", "(*sigh*) I wish I could stay here forever...\n\t"
                + "Oh no! I forgot I need to get back home to help Mom prepare for the Annual Festival! Oh, you've gone and done it "
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
        MainGame.dialoguelnln("Strange Voice 1", "Ow! Was that necess- (*gasp*) my *hair*!");
        MainGame.dialoguelnln("Strange Voice 2", "Haha! It's a step up from what it was before.");
        MainGame.dialoguelnln("Strange Voice 1", "Oh shut it, Fultra. I spent so long on it today for the festival...");
        MainGame.printlnlnWait("Anahita realized who the voices belonged to: her best friend Gaea and her boyfriend, Fultra.", 25, 1500);
        
        MainGame.promptToEnter();
        //-----------------------------------------------------------------------------------------------------------------------
        
        MainGame.printlnlnWait("She ran up to them, her hands covering her mouth and a guilty expression.", 25, 1500);
        MainGame.dialoguelnln("Anahita", "Oh my gosh! I'm so sorry Gaea. Are you okay?");
        MainGame.dialoguelnln("Gaea", "(*sniff*) I'll be fine... I guess...");
        MainGame.dialoguelnln("Fultra", "You'll know she'll be okay, Ana. She's more concerned about her hair than anything else.");
        MainGame.dialoguelnln("Gaea", "Don't down play it like it's nothing! I have to redo it all before the festival now!");
        MainGame.dialoguelnln("Anahita", "I promise I'll help you clean up a bit, okay? What're you guys doing here though?");
        MainGame.dialoguelnln("Gaea", "We were picking flowers to give to my cousin, Fleur. We have 4 baskets ready to go!");
        MainGame.dialoguelnln("Fultra", "And we thought we heard your voice, so we decided to see if it was you.\n\tAnd it "
                + "clearly was considering the damage done.");
        MainGame.dialoguelnln("Gaea", "It's fine, it's fine! Let's just move on. What're you doing, Ana?");
        MainGame.dialoguelnln("Anahita", "I was at Purity Beach by myself for a while when I lost track of time. I need to get\n\t"
                + "back home to see if my family needs help with preparations.");
        MainGame.dialoguelnln("Gaea", "Why don't you let us accompany you? We can stop by Degon afterwards anyway.");
        MainGame.dialoguelnln("Fultra", "And maybe we'll prevent another bad hair day for someone else...");
        MainGame.printlnlnWait("Gaea then playfully punched Fultra's arm, making Anahita laugh a little.", 25, 1000);
        MainGame.dialoguelnln("Anahita", "I'd love if you guys could help me! Thank you so much.");
        MainGame.dialoguelnln("Fultra", "Not a problem. You have 'Fearless Thunder' with you, so there's nothing to fear!");
        MainGame.dialoguelnln("Gaea", "(*sigh*) There he goes again, boosting that ego. Come on, Ana. Let's go!");
        
        MainGame.promptToEnter();
        //---------------------------------------------------------------------------------------------------------------------
        
        MainGame.printlnlnWait("Congratulations! Gaea and Fultra joined your team!", 25, 150);
        
        MainGame.promptToEnter();
        
        MainGame.printWithBreaks("You now have access to Fultra, one of the strongest Pulchrians!/He has a unique class"
                + " called the 'All-Rounder.'/All his stats are average and realatively even, but what really makes him "
                + "shine is his Buff Attack called 'Charge!'/ With it, he increases all his stats for a certain time, "
                + "but it has a large cooldown, so be mindful!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene right after forest tutorial.
     */
    public static void opiconCutscene2()
    {
        MainGame.clearScreen();
        
        print("The three friends let out a collective sigh after defeating the enemies and gave each other "
                + "high fives.");
        
        MainGame.dialoguelnln("Fultra", "Phew! That Krobble seemed very hostile. We handled it quite well though!");
        MainGame.dialoguelnln("Gaea", "Yeah! Nice healing back there, Ana!");
        MainGame.dialoguelnln("Anahita", "Thanks! And thank you guys for helping me.\n\tI don't think I would've been able to "
                + "handle them all on my own.");
        MainGame.dialoguelnln("Gaea", "But of course! We wouldn't just let you *die* here, right Fultra?");
        MainGame.dialoguelnln("Fultra", "... r-right... yeah...");
        
        MainGame.printlnlnWait("Gaea punched Fultra on the arm again.", 25, 1500);
        
        MainGame.dialoguelnln("Gaea", "That's not cool Fultra!");
        MainGame.dialoguelnln("Fultra", "Haha, you know I'm kidding! Jokes aside, we're happy to help you anytime, Ana.\n\t"
                + "Let's move on to Aquammoda then, shall we?");
        MainGame.dialoguelnln("Gaea and Anahita", "Sounds good!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene when first entering Aquammoda.
     */
    public static void aquammodaCutscene()
    {
        MainGame.clearScreen();
        
        print("Anahita and the others safely arrived at Aquammoda, Anahita's home.");
        
        MainGame.dialoguelnln("Anahita", "Awesome -- we made it! Thank you guys so much for accompanying me!\n\t"
                + "I need to check on my family first, and then we can go to Degon.");
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
        MainGame.dialoguelnln("Anahita", "Oh, okay... Again, I'm really sorry, Mom... Is there anything else you might "
                + "need help with?");
        MainGame.dialoguelnln("Merda", "No, I think we covered everything.\n\tI thought you would start feeling guilty after "
                + "I saw you were late, so Brinlee and I made a few extra just in case.");
        print("Both Anahita and Gaea's faces lit up when Merda brought a plate of extra Cinnamon Rolls for them./They beemed "
                + "with glee and Fultra couldn't help but smile at their energy.");
        MainGame.dialoguelnln("Gaea", "Awesome! Merda, you're amazing! Oh, and they smell even better!");
        print("Merda smiled and laughed as the girls indulged in the treats.");
        MainGame.dialoguelnln("Merda", "Fultra, would you care for one?");
        MainGame.dialoguelnln("Fultra", "Thank you, but I think I'll have to pass for now.");
        
        MainGame.promptToEnter();
        //--------------------------------------------------------------------------------------------------------------------
        
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
        MainGame.dialoguelnln("Merda", "Well, thank you, Fultra. You all clearly seemed to have enjoyed them. Hopefully "
                + "those\n\tat the festival do too.");
        MainGame.dialoguelnln("Merda", "Brinlee and I will be leaving soon for the Annual Festival. I'll give you guys some "
                + "extras before we go though.");
        MainGame.dialoguelnln("Anahita", "Oh, wonderful! Thank you so much. You're the best.");
        print("Anahita gave Merda a hug before giving the others her attention.");
        MainGame.dialoguelnln("Anahita", "Alright! Off to Degon then, right?");
        MainGame.dialoguelnln("Gaea", "Yup! I'm ready when you guys are.");
        MainGame.dialoguelnln("Fultra", "Let's go then!");
        
        MainGame.promptToEnter();
        
        MainGame.printlnln("You received 3 Cinnamon Rolls!", 25);
        MainGame.addToInventory(Item.getHealingItem("Cinnamon Roll"), 3);
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscecne for when the player first enters Degon.
     */
    public static void degonCutscene()
    {
        MainGame.dialoguelnln("Gaea", "Aaaand we made it! Alright, this is our last stop before the Annual Festival, and "
                + "then\n\twe can party!");
        MainGame.dialoguelnln("Fultra", "I've been waiting all day! It'll be nice to catch up with some people from the other "
                + "towns.");
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
        MainGame.dialoguelnln("Fleur", "I see -- so that's why you're late. Well, we have the flowers now, and that's all that "
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
        MainGame.dialoguelnln("Gaea", "Y'know what? You're right. I'll talk to him tonight. See -- this is why I appreciate "
                + "you.");
        print("Anahita laughed as she placed a few flowers in Gaea's hair.");
        MainGame.dialoguelnln("Anahita", "Done! I hope you like it.");
        
        print("Gaea looked in a mirror and admired Anahita's handiwork. Her hair was now neatly braided with flowers in the "
                + "front.");
        
        MainGame.dialoguelnln("Gaea", "Oh, Ana, it's amazing! Thank you so much! It's much better than anything *I've* done!\n\t"
                + "I didn't think that was possible!");
        MainGame.dialoguelnln("Anahita", "What can I say -- I'm a girl with many talents.");
        
        //-----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("The two laughed and quickly left Gaea's home. They caught up with Fultra right as Fleur's decoration team left "
                + "to finish\n\tthe last decorations at Zoni Village.");
        
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
        MainGame.dialoguelnln("Calliou", "NOW YOU KNOW!! HERE -- TAKE SOME!!! BEANS ARE AMAZING!!!");
        
        MainGame.printlnln("You received 3 Orange Beans!", 25);
        MainGame.addToInventory(Item.getBuffItem("Orange Bean"), 3);
        
        MainGame.dialoguelnln("Calliou", "REMEMBER THIS IF YOU'RE IN A TOUGH SITUATION -- 'BEANS MAKE YOU STRONGER FOR LONGER"
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
     * Cutscene for when the player first enters Degon.
     */
    public static void zoniCityCutscene()
    {
        MainGame.dialoguelnln("Anahita", "Finally! We made it!!");
        
        print("Anahita and the others rush into Zoni Village and admire the animated area. People were going back and forth, "
                + "helping with last minute/decorations and setting up food. The group saw Fleur, busy guiding people to set "
                + "up the flowers Gaea and Fultra picked earlier that day.");
        
        print("The group stared in amazement at the beauty of the town. Gaea then started to sniff the air, a sweet aroma "
                + "captivating her.");
        
        MainGame.dialoguelnln("Gaea", "(*sniff sniff*) I can smell... Cinnamon Rolls... Triple Chocolate Meltdown... Apple "
                + "Pies... Oh, today is gonna be amazing!\n\tThere's desserts galore!");
        
        print("Fultra laughed at the enthusiasm of Gaea's sleuthing nose and attitude.");
        
        MainGame.dialoguelnln("Fultra", "You two will have a field day with the food. Don't forget the annual dance though! "
                + "The music, the food...\n\teverything will be wonderful.");
        
        MainGame.dialoguelnln("Anahita", "I agree! This is always the best time of the year. Before we do anything else, we "
                + "should talk to everyone to see if\n\tanyone needs help. I also see some familar faces, so let's go say hi!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene for when the player talks to Elder Vitorem at Zoni Village.
     */
    public static void vitoremCutscene()
    {
        print("Vitorem's cutscene will be here. It should introduce the concept of the elders to the player and how the title "
                + "is passed down.");
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
                + "His strength and endurance/is admired by many. He's known the group for years, but the journey to the Infol "
                + "is too strenous for frequent visits. Even though/he's physicially strong, his emotions "
                + "is something he fights with frequently.");
        
        MainGame.dialoguelnln("Fultra", "It's been too long! How's your family doing?");
        
        MainGame.dialoguelnln("Calmus", "I'm just taking care of them as normal and helping when I can. "
                + "Despite everything, we're still holding strong.\n\tBut how are you guys?");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Gaea", "We're doing quite well! We're excited to eat some wonderful food, listen to the "
                + "music -- everything! You know\n\twe love this time of the year.");
        
        MainGame.dialoguelnln("Calmus", "I don't blame you. It's amazing to watch all of Pulchra come together in harmony... "
                + "It's beautiful.\n\tI should let you guys go though! Go enjoy the festival, but I'd like to catch up before "
                + "you guys leave.");
        
        MainGame.dialoguelnln("Gaea", "We definitely should! We'll see you again soon, Calmus!");
        
        MainGame.dialoguelnln("Calmus", "And before you guys leave, take this! My grandmother made extra.");
        
        MainGame.promptToEnter();
        
        MainGame.printlnln("You received an Apple Pie!", 25);
        MainGame.addToInventory(Item.getHealingItem("Apple Pie"), 1);
        
        MainGame.promptToEnter();
        
        print("Anahita and Gaea smelled the wonderful scent from the pie.");
        
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
        
        print("The group approaches Frigs, a thin-looking, young man. He was watching people put the decorations up as he "
                + "played with a snowflake on/his finger. Anahita snuck up behind him and poked him with her fingers ready "
                + "to use Tsunami Shot.");

        MainGame.dialoguelnln("Anahita", "Boo!");
        
        print("Frigs let out a cry that was uncharacteristic for someone that's known to be nonchalant. He pushed a laughing "
                + "Anahita away as/he recollected himself. Gaea and Fultra were laughing too.");
        
        MainGame.dialoguelnln("Frigs", "Oh my gosh... Ana! Can you not do that?");

        print("Frigs tried to be angry but couldn't help but laugh with the group.");
        
        print("Frigs, a normally witty, yet collected person is another childhood friend of Anahita's. They couldn't visit "
                + "each other too often, so/they would talk long into the night after the Annual Festival.");
        
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
        
        MainGame.dialoguelnln("Gaea", "Why are you always so pretenious? Get off your high horse already! You're always a-");
        
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
        print("After the group finished talking to other townrs, a bell chimed and a wave of people gathered to the center "
                + "of Zoni Village./All the decorations are hung up, beautifully representing the Pulchrian culture and "
                + "lifestyle.");
        
        print("Anahita, Gaea, and Fulchra follow the crowd and find the person who rang the bell. It's an older looking man, "
                + "with strong physique./He's known as Elder Vitorem, the leader of Pulchra. A kind, caring, and "
                + "compassionate man.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Elder Vitorem", "My dear Pulchrians, welcome to this year's Annual Festival!");
        
        print("An uproar of cheer raised as people hugged one another. Streamers were flying in the air, instruments playing, "
                + "cymbals crashing./Once it was quiet again, Elder Vitorem continued.");
        
        MainGame.dialoguelnln("Elder Vitorem", "Today, we celebrate another year of peace, and prosperity. Thank you all for "
                + "all you've done. May\n\tthis mark the start of another year of goodness for us all. Please -- enjoy the food, "
                + "enjoy each other's company,/and let's enjoy the celebration!");
        
        print("After he finished, the music started again. Dancers from Aquammoda and Infol started performing a "
                + "traditional dance./People walked around, talking, laughing, cheering with one another. It was an amazing "
                + "event. Some would have even considered it perfect.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Calmus and Frigs joined with Anahita, Gaea, and Fultra. The circle of friends talked and caught up with what "
                + "happened in their lives/the past year. Ninlil was with her training partner, Ilven, off to the side. They "
                + "were also talking, but Ninlil didn't/engage in much conversation otherwise. Eventually, someone called "
                + "Ilven elsewhere, leaving Ninlil on her own.");
        
        MainGame.dialoguelnln("Anahita", "Hm...");
        
        MainGame.dialoguelnln("Gaea", "What's up Ana? Something on your mind?");
        
        MainGame.dialoguelnln("Anahita", "I'm just thinking about Ninlil. I know she's difficult, but I think I'll invite her "
                + "over. Er, at least, I'll try to.");
        
        print("Gaea was surprised at Anahita's desire to invite Ninlil to join the group.");
        
        MainGame.dialoguelnln("Gaea", "Why? Did you already forget how she acted earlier?");
        
        MainGame.dialoguelnln("Anahita", "I know, but she seems to only have Ilven to talk to. It wouldn't hurt to try.");
        
        MainGame.dialoguelnln("Frigs", "I'm not opposed to it. I have to agree and say that no harm could truly be done.");
        
        print("Gaea let out an exasperated sigh and then shook her head.");
        
        MainGame.dialoguelnln("Gaea", "...Fine... Don't be surprised if you fail though.");
        
        print("Anahita approached Ninlil and started conversation. Ninlil surprised Anahita and engaged with her. After a few "
                + "minutes, the two/returned to the rest of the group. Gaea's jaw dropped, but she quickly recollected herself."
                + "The other guys waved Ninlil in.");
        
        MainGame.dialoguelnln("Anahita", "I'm sure everyone already knows Ninlil, but she said she's willing to be with us for "
                + "a while.");
        
        MainGame.dialoguelnln("Ninlil", "It's better than being alone, I guess.");
        
        print("Ninlil shrugged and sat with the group. Despite Ninlil not really liking the entire group, she was "
                + "thankful for being invited to be with/them, but she would never say that. She was quiet for most of the "
                + "conversation, but she was attentive and kept to herself.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("The night went on strong, with people still celebrating. The music and dancing was still going, but then...");
        
        MainGame.wait(1500);
        
        MainGame.dialoguelnln("Anahita", "Do... do you guys see that bright light in the sky?");
        
        print("The group looked up and saw a bright red light in the dark night's sky. Then, the light multiplied.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Suddenly, a red laser blasted into the ground in the midst of the festival. Screams erupted instantly, causing "
                + "panic and for/people to scatter. Anahita and the rest of the group immediately went on the defensive.");
        
        MainGame.dialoguelnln("Gaea", "What's happening?!");
        
        MainGame.dialoguelnln("Fultra", "It looks like we're under attack! We need to help everyone however we can!");
        
        print("The group all nodded and went around the festival grounds to help people get to safety. However, the more time "
                + "that passed, the more lasers were fired.");
        
        print("Fires started from where the lasers blasted, consuming the decorations. People were fleeing the area as quickly "
                + "as they could.");
        
        MainGame.dialoguelnln("Elder Vitorem", "Everyone get to safety -- we're under attack! Take the children some where "
                + "safe! If you\n\tare capable of fighting, please stay here to defend against the invaders!!!");
        
        print("Anahita and the others ran up to Elder Vitorem.");
        
        MainGame.dialoguelnln("Fultra", "Elder Vitorem! We're here to help in whatever way we can!");
        
        MainGame.dialoguelnln("Elder Vitorem", "Ah, Fultra! Thank goodness you're all here. Get ready -- I fear that this is a "
                + "terrible threat.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("A laser shot next to wear the group was standing, causing them all to fall. A robot then zoomed in next to them "
                + "all. It had/a sleek design, its metal covering glimmering in the light of the surrounding fire. After it, "
                + "many other robots came flying in./They started to attack the townrs that were trying to flee.");
        
        print("Other townrs that could fight grouped together to fight. Verg, Frigs' brother; Ilven, Ninlil's training "
                + "partner; Lac, Anahita's father,/among others, were ready.");
        
        print("The robot that attacked close to Anahita and the others was on the offensive and started to attack them!");
        
        MainGame.dialoguelnln("Anahita", "Guys, be careful! We need to work together if we're going to beat this thing!");
        
        print("Everyone nodded and joined Anahita to get ready to fight.");
        
        MainGame.promptToEnter();
        
        MainGame.printlnln("Congratulations! Calmus, Frigs, and Ninlil joined your party.", 25);
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene that plays after the player defeats the R.E.S.I. Bot.
     */
    public static void warCutscene2()
    {
        print("After a difficult fight, Anahita and the others successfully defeated the R.E.S.I. Bot, leaving a dismantled "
                + "pile of metal on the ground./A green liquid started seeping out from the remains.");
        
        print("As the group tried to recover from the fight, more screams erupted as more R.E.S.I Bots swarmed in. The night "
                + "was filled with red, blinding/lights. A loud, collective roar was coming the robots as they flew in the "
                + "air.");
        
        MainGame.dialoguelnln("Calmus", "(*huff*) Is... is everyone okay?");
        
        MainGame.dialoguelnln("Gaea", "Yeah, I think so. If anyone needs to be healed, Ana and I can help you.");
        
        MainGame.dialoguelnln("Elder Vitorm", "Thank you all for your help. Please, do whatever you can to protect the others.");
        
        print("Just then, another robot flew in and attacked Elder Vitorem, sharp, metal claws pinning him down, digging into "
                + "his skin.");
        
        //-----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Fultra", "Elder Vitorem!");
        
        print("Fultra tried to get to the Elder, but 5 more R.E.S.I Bots blocked him. Fultra, consumed by fear, backs away "
                + "slowly.");
        
        MainGame.dialoguelnln("Elder Vitorem", "Argh! Back away, Fultra! You all barely handled one on your own...");
        
        MainGame.dialoguelnln("???", "He's right. I'm impressed that you were able to defeat it in the first place.");
        
        print("The group looked up to see where the mysterious voice came from. They see a man wearing armor similar to "
                + "that of the R.E.S.I Bots./The metal covers him perfectly and shines in the light from the flames. He slowly "
                + "descends from the air as he controls the/jetpack attached to his armor.");
        
        MainGame.dialoguelnln("Elder Vitorem", "Wh-who are you? What is all of this??");
        
        print("The mysterious man slowly walks toward the Elder and crouches in front of him. The Bots that prevented Fultra "
                + "from approaching him/let the mysterious man to him.");
        
        MainGame.dialoguelnln("???", "I am... merely a man. A man with goals. Ambition. A hope. You may call me Irwin.");
        
        MainGame.dialoguelnln("Anahita", "Why have you caused all of this? We don't know who you are and you attack us out of "
                + "know where? Why??");
        
        MainGame.dialoguelnln("Irwin", "Anahtia... That is your name, correct?");
        
        print("The group let out a collective gasp, surprised that Irwin knows her name.");

        MainGame.dialoguelnln("Irwin", "Anahita, I beleive that you have more pressing matters to attend to. Your father seems "
                + "to be having some difficultly...");
        
        MainGame.dialoguelnln("Anahita", "Wh-what...?");
        
        print("Anahita scaned the area quickly and remembers that her father, Lac, was also fighting to defend against the "
                + "Bots. She sees him/also pinned to the ground, greatly injured. Without a second thought, she leaves the "
                + "group to go help her beloved father.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Irwin", "The rest of you... I believe a young woman named Fleur, a nice woman named Pheu, and "
                + "two young men named\n\tVerg and Ilven are having issues too. I recommend you go help them if you "
                + "*truly* care for them.");
        
        print("As each name was spoken, Gaea, Calmus, Frigs, and Ninlil's eyes widened. They all quickly left to go find "
                + "their loved ones./Fultra, Irwin, and Elder Vitorem, who is still pinned to the "
                + "ground were the only ones left.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Irwin smirks as he stands up and puts his hands together behind his back.");
        
        MainGame.dialoguelnln("Irwin", "Now... It's the two people I've been wanting to see.");
        
        MainGame.dialoguelnln("Fultra", "Cut the crap, Irwin. What do you want? We've asked you so many times already. What is "
                + "happening??");
        
        MainGame.dialoguelnln("Irwin", "Calm down, Fultra. I can explain this all to you, if you'd join me.");
        
        MainGame.dialoguelnln("Fultra", "Why would I *ever* do that?! You've caused so much destruction! "
                + "We've done *nothing* wrong!");
        
        print("The Bot holding the Elder down started to tighten its grip, harming him. The Elder let out a scream of "
                + "pain. Fear/started to consume Fultra even more.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Fultra", "H-hey! L-let the Elder go!");
        
        print("Irwin mockingly laughs at the quiver in Fultra's voice.");
        
        MainGame.dialoguelnln("Irwin", "For someone known as 'Fearless Thunder,' you know how to express what you supposedly "
                + "lack. The irony is amusing.\n\tRegardless, you possess exactly what I'm looking for. With you working with "
                + "me, we can save the entire world!\n\tWe can save people from pain. We can solve every issue this world has. "
                + "Together. It all starts with you.");
        
        MainGame.dialoguelnln("Fultra", "You're sick in the head! 'We can save people from pain.' If that's the case, why "
                + "are you here *causing* pain??");
        
        MainGame.dialoguelnln("Irwin", "Like I said, I can explain this all to you if you join me. Otherwise, I'll take more..."
                + "drastic measures.");
        
        print("There was a brief pause in the conversation. Fultra looked around at the chaos that was brought by this one "
                + "man. Fultra didn't/understand Irwin's goals, and at this point, he wasn't sure if he wanted to at all. "
                + "He looked at the struggling Elder,/who was trying to free himself from the metalic grip without any luck.");
        
        print("Depite his nickname of Fearless Thunder, Fultra was struggling to fight against it. Pulchra had never seen a "
                + "threat like this before./This threat was deadly. Threatening to take lives away. Fultra swallowed hard to "
                + "keep composure before speaking.");
        
        MainGame.dialoguelnln("Fultra", "No. I'm not joining you. I have no reason to. If you're going to cause this much "
                + "chaos and attack\n\twithout warning, I would be stupid to help you with whatever you're doing.");
        
        print("Irwin let out an exasperated sigh. He then nodded slowly as he looked to the side.");
        
        MainGame.dialoguelnln("Irwin", "... I should've expected that answer. Well, this is fine. I can make arrangements.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Suddenly, Elder Vitorem let out another yell of pain as he was harshly attacked from the Bot that pinned him./"
                + "This caused a wound that would be too great to heal.");
        
        MainGame.dialoguelnln("Fultra", "NOOOOOO!");
        
        print("Fultra screamed in anger and went to attack Irwin. His body was charged with electricity as he went for his "
                + "attack. Irwin/efforlessly dodged the attack and quickly sucker punched Fultra in the back. The metalic "
                + "suit proctected him from being/harmed by the electricity. Fultra dropepd to the ground after taking "
                + "the sharp blow.");
        
        MainGame.dialoguelnln("Irwin", "You've left me no choice, Fultra. This is how things will be. You'll come to understand "
                + "soon.");
        
        print("The 5 R.E.S.I. Bots that were guarding Elder Vitorem surrounded Fultra and attacked him instead, causing him to "
                + "yell in pain./Gaea, who was successfully leaving with Fleur, saw this before leaving. She felt her heart "
                + "choke and desperately wanted to go back/to get him, but she knew it was not wise.As she left "
                + "the now destroyed Zoni Village, she had tears streaming down/her face, with Fultra's cries ringing in her "
                + "ears.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        MainGame.ellipsislnln();
        
        MainGame.promptToEnter();
    }
    
    public static void postWarCutscene()
    {
        print("A week after the horrible events that took place at Zoni Village, Anahita, Gaea, and Calmus are at "
                + "Anahita's/house in Aquammoda. The trio and Merda were having conversation as Brinlee laid across "
                + "Merda's lap.");
        
        MainGame.dialoguelnln("Merda", "(*sniff*) Oh, Lac... I can't believe he died... It's too soon... too soon...");
        
        MainGame.dialoguelnln("Anahita", "I... I couldn't heal him... His wounds were too severe once I got to him... The "
                + "look he\n\tgave me before he died... I...");
        
        MainGame.promptToEnter();
        
        print("The room fell quiet for a moment.");
        
        MainGame.ellipsislnln();
        MainGame.wait(500);
        
        MainGame.dialoguelnln("Gaea", "It's okay, Ana... I'm really, really sorry... All of this has been... terrible...");
        
        MainGame.promptToEnter();
        
        print("The room fell quiet once more.");
        
        MainGame.ellipsislnln();
        MainGame.wait(500);
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Calmus", "We... we should find the others. Frigs and Ninlil, I mean. We need to confront that "
                + "man, Irwin, and stop him.");
        
        MainGame.dialoguelnln("Gaea", "I agree, but how would we defeat him? Those robots are already hard enought to fight as "
                + "is.");
        
        MainGame.dialoguelnln("Calmus", "To be honest, I'm not too sure. It's better for us to at least find them first. We "
                + "can probably figure\n\tout how to defeat them and make a good plan with them helping too.");
        
        print("Anahita and Gaea nodded in agreement.");
        
        MainGame.dialoguelnln("Merda", "I don't want to admit it, but you're right. It'll be better for you all to fight "
                + "together. All I ask...");
        
        print("Merda choked on her words slightly, fearful and emotional.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Merda", "All I ask is that you're careful. Those *things* that attacked are flying all over "
                + "Pulchra now, so you'd most\n\tlikely have to fight them again. *Please* be careful. I can't go through this "
                + "pain again.");
        
        print("Anahita wrapped her arms around her mother and let out a shaky breath.");
        
        MainGame.dialoguelnln("Anahita", "I promise, we'll be careful. We'll do what we can be bring peace back. We'll "
                + "stop him... For Dad, Verg,\n\tIlven, and... Fultra...");
        
        print("Gaea choked up slightly at the mention of Fultra's name. Calmus put a gentle hand on her shoulder to comfort "
                + "her.");
        
        print("After she witnessed him getting attacked as she left Zoni Village, she informed everyone of what she saw. "
                + "They all concluded that he/died. No one could have survived being attack by 5 of those robots "
                + "at once.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Calmus", "The way to Frigs is a lot more strenuous. Let's go to Aerogan first to find "
                + "Ninlil.");
        
        print("Anahita and Gaea nodded. The group left Merda's house, saying goodbye. Anahita's eyes watered as they left. "
                + "She quickly wiped her face before muttering to herself.");
        
        MainGame.dialoguelnln("Anahita", "We'll be back, Mom... And I'll make Dad proud. I promise.");
        
        //----------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("You can now change a character's class./In the menu, select Options > View Team and select a character. "
                + "Then, select 'Change Class.'/Changing a character's class will allow for different growth patterns and "
                + "allow some characters to become their strongest./Experiment to see what builds help grow Anahita and the "
                + "others the best.");
    }
    
    /**
     * A cutscene that plays when the player talks to fleur in the second phase of the game.
     */
    public static void fleurCutscene2()
    {
        MainGame.dialoguelnln("Fleur", "My cutscene starts here!");
    }
    
    /**
     * A cutscene that plays when the player first enters the Aerogan.
     */
    public static void aeroganCutscene()
    {
        print("Anahita and the others safely arrived to the Aerogan. There were only a handful of homes that were fully "
                + "intact. Others/were either damaged, burned, or completely missing. Debris was everywhere, with only a "
                + "few residents cleaning up.");
        
        MainGame.dialoguelnln("Calmus", "This place is a mess... I wouldn't have expected it to be *this* bad.");
        MainGame.dialoguelnln("Gaea", "I know, it's awful. This place was always so pretty too.");
        MainGame.dialoguelnln("Anahita", "Yeah...Let's see if we can find Ninlil. Hopefully we find her quickly.");
    }
    
    /**
     * A cutscene that plays when the player talks to Elder Nu for the first time.
     */
    public static void elderNuCutscene()
    {
        MainGame.dialoguelnln("Anahita", "I see Elder Nu over there. Let's ask him if he knows where Ninlil is.");
        
        print("The group walked over to a short man. He was bald and hunched over with his hands behind his back. "
                + "He only had a few strands of/hair left on his head.");
        
        MainGame.dialoguelnln("Anahita", "Elder Nu?");
        
        print("The Elder turned around to address the group. He greeted them with a warm yet slightly pained smile. He "
                + "smacked his lips/before speaking.");
        
        MainGame.dialoguelnln("Elder Nu", "(*smack*) Ah, hello children. How are you all doing? I hope better than we are.");
        
        MainGame.dialoguelnln("Gaea", "We're doing the best we can, Elder. This place definitely took a hit.");
        
        MainGame.dialoguelnln("Elder Nu", "Indeed it did... but do you all need anything? I don't know how much we "
                + "can offer, but we can do our best.");
        
        print("The group explained their goals in saving Pulchra to Elder Nu and the importance of their journey.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Elder Nu", "(*smack*) Ah, I see... You all have a strenuous journey ahead of you. You will have our "
                + "support then.");
        
        MainGame.dialoguelnln("Calmus", "Thank you so much, Elder. We appreciate it. We do need Ninlil to join us, however. "
                + "She's strong. We\n\tthink she would be perfect to aid us.");
        
        MainGame.dialoguelnln("Anahita", "Do you know where she is?");
        
        MainGame.dialoguelnln("Elder Nu", "Yes, actually. She's at Tempest Tower, just south of here. (*smack*) She's been "
                + "greiving since we lost\n\tIlven... He was one of our best and the closest person to her... When you "
                + "see her, be careful.\n\tI doubt she's in the right headspace -- she may need some convincing and "
                + "consolation.");
        
        print("Gaea's heart is normally hardened toward Ninlil, but hearing Elder Nu speak softened her. She felt herself "
                + "start to gain compassion/and empathy for her. Now knowing the pain of loss is a strong factor for this.");
        
        MainGame.dialoguelnln("Anahita", "Thank you so much, Elder. We'll go over there to see if we can find her.");
        
        MainGame.dialoguelnln("Elder Nu", "I wish you all the best, children. Be strong.");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A custcene that plays when entering Tempest Tower for the first time.
     */
    public static void tempestTowerCutscene()
    {
        print("The group arrived to the entrance of Tempest Tower. Its shadow loomed over them. Its peak pierced the sky as "
                + "clouds/encircled it. It exuded an air of majesty and power. Anahita and Gaea were too breath taken to speak.");
        
        MainGame.dialoguelnln("Calmus", "This is a lot larger than I thought it would be... It is amazing though.");
        
        print("Still flabergasted, Anahita and Gaea slowly nodded.");
        
        MainGame.dialoguelnln("Gaea", "And we have to climb this *entire* thing? Ugh, I'm gonna get all sweaty!");
        
        MainGame.dialoguelnln("Calmus", "It's better for us to get sweaty than forever lose the peace we once had.");
        
        MainGame.dialoguelnln("Gaea", "... Yeah, you're right... (*sigh*) Let's start climbing this thing, then.");
        
        print("Gaea and Calmus opened the two giant doors that lead into the tower and stepped inside. Anahita was still "
                + "outside gawking at/the sheer size of it. Gaea quickly popped backed out and dragged her inside.");
    }
    
    /**
     * A cutscene that plays before starting the Ninlil Boss fight.
     */
    public static void foundNinlilCutscene()
    {
        MainGame.dialoguelnln("Gaea", "(*pant*) (*pant*) UGH. How much further do we have until we find Ninlil? This is "
                + "ridiculous.\n\tI *really* need a shower, and it's so COLD up here.");
        
        print("The group had been climbing up the tower's floors for about an hour. They were sweaty, cold, and tired.");
        
        MainGame.dialoguelnln("Anahita", "(*huff*) We have to be close. And the creatures here haven't been the easiest to "
                + "fight against either.");
        
        MainGame.dialoguelnln("Calmus", "Don't worry guys -- I think I see the exit at the top of this staircase!");
        
        print("The group finally made it to the climax of the tower. They were surrounded by the crescent clouds and a cold "
                + "breeze./Gaea and Anahita shivered, so Calmus supplied a small flame to keep them warm.");
        
        print("The peak of the tower was a giant platform. At its center, there was a bell, and Ninlil was in front of it. "
                + "She was/occasionally ringing the bell, and slight sobbing could be heard between the ringing.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("The group slowly approached Ninlil, with Anahtia being at the front. Despite her being exhausted, she was "
                + "determined/to talk to Ninlil and be there for her.");
        
        print("Ninlil heard their footsteps behind her. She caused a breeze to blow only in front of her to dry her tears "
                + "before/she spoke. She still had her back turned to the group.");
        
        MainGame.dialoguelnln("Ninlil", "(*sniff*) What do you guys *want*? Please, just leave me alone.");
                
        print("Gaea quickly whispered to Anahita before Anahita said anything.");
        
        MainGame.dialoguelnln("Gaea", "Remember -- we need to be careful of how we say things. She's still grieving.");
        
        print("Anahita nodded and looked at Calmus to see him give a look of reassurance. She took a breath before speaking.");
        
        MainGame.dialoguelnln("Anahita", "Ninlil, we aren't here to irriate you; we just want to talk to you, to be here for "
                + "you.");
        
        print("Gaea's heart was softened, yes, but she still couldn't believe how kind Anahita had treated Ninlil. She "
                + "always had/a kind heart, and Gaea couldn't understand it all the time, even when the other person "
                + "treated Anahita terribly./However, Gaea admired it.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Ninlil turned around acutely after hearing Anahita.");
        
        MainGame.dialoguelnln("Ninlil", "What is there to talk about? I lost the ONLY person that meant so much to me! And "
                + "now he's gone.\n\t(*sniff*) So what am I supposed to do?? I have *no one* now.");
        
        print("Gaea grimmaced after hearing this. Calmus saw this and put a hand on her shoulder to comfort her. She looked "
                + "at him/and gaved a pained smile.");
        
        MainGame.dialoguelnln("Anahita", "I understand that, Ninlil, trust me, I-");
        
        MainGame.dialoguelnln("Ninlil", "No, Anahita... You know what, I could've saved him. Instead of fighting with you guys, "
                + "I could've been\n\tby his side to help him. If I was there for him like he's been there for me, *I could "
                + "have saved\n\thim*. Instead, I was there with you all...");
        
        print("Ninlil didn't know how to feel, so she started putting the blame on Anahita and the others.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Ninlil", "I'm strong. I'm a strong warrior. I know I could've saved him...!");
        
        MainGame.dialoguelnln("Anahita", "No one is saying you aren't strong Ninlil! It's a difficult, confusing time right "
                + "now. I just-");
        
        MainGame.dialoguelnln("Ninlil", "No, it's *your* fault. You should've just left me alone that day. Then everything "
                + "would be\n\tfine!");
        
        print("Ninlil's voice started breaking as she spoke.");
        
        MainGame.dialoguelnln("Ninlil", "I just want him back...");
        
        MainGame.ellipsislnln();
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Ninlil", "... you know what, no. Anahita, fight me right now. I'll prove that I could've done "
                + "it. I know I could've.");
        
        MainGame.dialoguelnln("Gaea", "Wait, what's fighitng going to sol-");
        
        print("Calmus immediately tapped her to let her know not to say anything. She then covered her mouth to keep quiet.");
        
        MainGame.dialoguelnln("Anahita", "No, that's okay. I understand. If it helps, I will fight you.");
        
        MainGame.dialoguelnln("Ninlil", "Then let's fight. I'll prove myself here.");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene that plays after defeating the Ninlil boss at Tempest Tower.
     */
    public static void defeatedNinlilCutscene()
    {
        print("After a fierce fight, Anahita was claimed the victor. Both Anahita and Ninlil were panting from the fight as "
                + "Gaea and Calmus/smiled at the victory. However, their smiles quickly dropped as Ninlil fell to her knees "
                + "and started sobbing./Between tears and gasps of air, she spoke.");
        
        MainGame.dialoguelnln("Ninlil", "how is any of this fair...? i just want him back. what am i doing wrong...?");
        
        print("Anahita knelt down beside Ninlil and consoled her. Gaea and Calmus also approached the two girls on the "
                + "ground.");
        
        MainGame.dialoguelnln("Anahita", "Ninlil, it's not fair to anyone, and it's not your fault he's gone. Trust me, I'm "
                + "dealing with the\n\tsame thing... My father, he...");
        
        print("Anahita paused for a second, the high winds filling the silence.");
        
        MainGame.ellipsislnln();
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Anahita", "My father died that day too. I wasn't able to get to him in time. I still think "
                + "about him everyday,\n\tbut I know that it isn't my fault.");
        
        print("Through teary eyes, Ninlil looked at Anahita with a look of empathy and slight shock.");
        
        MainGame.dialoguelnln("Ninlil", "I... I had no idea. I'm so sorry for your loss...");
        
        print("Ninlil was already showing a change of heart. Knowing that someone else experienced something similar was "
                + "comforting to her;/knowing that there's someone else to connect to was soothing to her.");
        
        print("Gaea felt prompted to share her pains with Ninlil in the hopes that it would also help.");
        
        MainGame.dialoguelnln("Gaea", "Yeah... It really isn't easy. Fultra, he... he also...");
        
        print("Calmus again comforted Gaea as she choked on her words. Her eyes started to water too.");
        
        MainGame.dialoguelnln("Anahita", "We also lost him during the fight...");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Ninlil", "O-oh... I had no idea... I'm truly sorry to you both...");
        
        print("Ninlil was starting to feel regretful for blaming the group for the death of Ilven. She was starting to "
                + "realize that/they -- the group she always found annoying since childhood -- was never her enemy. Instead, "
                + "she has the same/enemy as they do.");
        
        print("Ninlil wipes the tears from her eyes and stood up. Anahita followed suit.");
        
        MainGame.dialoguelnln("Ninlil", "I'm sorry for any trouble I caused you guys. I really do mean it, and I hope you can "
                + "all forgive me.");
        
        MainGame.dialoguelnln("Anahita", "Don't worry, Ninlil. I forgive you. I've always tried to be there for you, even "
                + "when\n\tyou pushed me away.");
        
        MainGame.dialoguelnln("Calmus", "The same here. I knew you were going through your own tribulations, but we appreciate "
                + "the apology, really.");
        
        print("Anahita, Calmus, and Ninlil all looked at Gaea. She folded arms and turned away after they all looked at her at "
                + "once.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Gaea", "Hey, there's no need to direct *all* attention towards me at once! But... (*sigh*) "
                + "yeah, I forgive\n\tyou, Ninlil. Life isn't easy... especialy when we lose those we love... Hopefully "
                + "we can\n\twork together and move beyond our past, though.");
        
        print("Calmus and Anahita looked at each other and smiled. Ninlil smiled slightly too.");
        
        MainGame.dialoguelnln("Ninlil", "We won't be perfect, but we'll see how we work together.");
        
        print("Anahita, Gaea, and Calmus all explained what their goals were in hopes of defeating Iwrin. Ninlil agreed to "
                + "join them/and they prepared to leave.");
        
        MainGame.dialoguelnln("Calmus", "Before we leave to find Frigs, can we go to Infol? I need to check on "
                + "my family first.\n\tI haven't been able to see them since I've been with you guys. I "
                + "don't want\n\tmy little sister to be overwhelemed on her own.");
        
        MainGame.dialoguelnln("Gaea", "Of course we can! It's on the way there, anyway. I hope they're doing okay.");
        
        MainGame.dialoguelnln("Calmus", "... me too...");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Congratulations! Ninlil joined your party!");
        
        MainGame.promptToEnter();
    }
    
    public static void infolCutscene()
    {
        MainGame.dialoguelnln("Calmus", "Oh no...");
        
        print("The group arrived to the Infol. It was in better shape than Aerogan, but it still experienced "
                + "major damage./Gaea put a consoling hand on Calmus.");
        
        MainGame.dialoguelnln("Gaea", "It's okay, Calmus. Let's see if we can find your family!");
        
        MainGame.dialoguelnln("Anahita", "We'll help them however we can while we're here. It's only right to do so.");
        
        MainGame.dialoguelnln("Ninlil", "Yeah... I don't know you too well, Calmus, but we'll do whatever you need to help.");
        
        print("Calmus closed his eyes and smiled as his anxiety lightened.");
        
        MainGame.dialoguelnln("Calmus", "Thank you all. It means a lot. The house is close by, so we can get there quickly.");
    }
    
    public static void elderVulcaCutscene()
    {
        print("The group entered Calmus' house to see his little sister, Lyra, and his grandmother who is the Elder of the "
                + "town./Elder Vulca was on a bed resting as Lyra sat next to her. When Lyra saw the group, "
                + "she jumped/up quickly and ran to give Calmus a hug.");
        
        MainGame.dialoguelnln("Lyra", "Oh, Calmus! I'm so happy to see you're okay!");
        
        print("Calmus embraced the hug and smiled at the joy his little sister exuded. The girls in the group smiled at the "
                + "heart warming moment.");
        
        MainGame.dialoguelnln("Calmus", "The same to you, Lyra.");
        
        print("As the siblings parted from the hug, Elder Vulca let out a terrible cough. The siblings then ran to her to "
                + "help./Anahita and the other girls followed to see what they could do. Calmus put a hand to the Elder's "
                + "forehead.");
        
        MainGame.dialoguelnln("Calmus", "Oh, grandma... Lyra, is she doing any better?");
        
        print("Lyra sadly shook her head.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Lyra", "Her condition only got worse since the invasion... I've tried to heal her, "
                + "but it doesn't\n\twork. She can barely speak without pain.");
        
        print("Calmus' head sank as he heard the news, and Ninlil clentched a fist in anger after hearing it.");
        
        MainGame.dialoguelnln("Anahita", "May I try to heal her? I might be able to do something, or at least ease the pain.");
        
        MainGame.dialoguelnln("Lyra", "Go for it, please. I know you're one of the better healers on Pulchra.");
        
        print("Anahita used Water Halo on the Elder. The room collectively held their breath as they waited for the result.");
        
        MainGame.ellipsislnln();
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Elder Vulca", "(*cough*) Oh, thank you, Anahita. (*cough*) It's not a full recovery, but the "
                + "pain is better now.");
        
        print("Anahita smiled as the room rejoyced at the healing. Lyra thanked Anahita with a very tight hug, and Calmus "
                + "was able to feel relieved.");
        
        MainGame.dialoguelnln("Anahita", "I know that it helped with the pain, but is there anything else we can do you make "
                + "better?");
        
        MainGame.dialoguelnln("Lyra", "I know something that can help! At the peak of Mount Volcan, there is a special "
                + "mineral that's\n\tformed because of the heat and ethrellium. It's purple in color and jagged. If you can "
                + "find it, I can use it to make a\n\tremedy! I wanted to get it before, but the creatures at the volcano "
                + "are too strong for me...");
        
        MainGame.dialoguelnln("Ninlil", "Don't worry -- we'll be able to get it for you. We're all starting to work quite well "
                + "together.");
        
        print("Gaea smiled proudly at the remark.");
        
        MainGame.dialoguelnln("Calmus", "We'll go there now. We'll be back soon.");
        
        MainGame.promptToEnter();
    }
    
    /**
     * Cutscene to be played when first entering Mount Volcan.
     */
    public static void mountVulcaCutscene()
    {
        print("The group made it safely to the base of Mount Volcan and started climbing. Simply being at the base of "
                + "the volcano/caused all the girls to sweat from the heat. Calmus felt perfectly fine.");
        
        MainGame.dialoguelnln("Gaea", "It. Is. So. HOT. I can't keep doing this over and over.");
        
        MainGame.dialoguelnln("Anahita", "We'll be okay... I hope. I'll try to provide water on the way up. This will be "
                + "*exhausting*.");
        
        MainGame.dialoguelnln("Ninlil", "Could we just... take breaks here and there? I don't know if I'll handle this heat "
                + "too well.");
        
        print("Calmus couldn't help but smile at the girls complaining about the heat. He came to the volcano often to train "
                + "his powers/so he is well adapted to the environment.");
        
        MainGame.dialoguelnln("Calmus", "As long as Ana can provide water, you guys should be okay. I will warn you though -- "
                + "it will only\n\tget hotter from here.");
        
        print("The girls all groaned at the warning which caused Calmus to laugh. Calmus led the group up the path as the "
                + "other trudged along begrudgingly.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
                
        print("As the group traveled up the volcano, they saw massive markings on the rocks, and giant holes in some parts "
                + "of the cliff walls.");
        
        MainGame.dialoguelnln("Ninlil", "Hey, uh, guys? Are these markings supposed to be here?");
        
        print("Ninlil pointed at one of the markings, which prompted Calmus to look at it. He touched it with his hand before "
                + "addressing the group.");
        
        MainGame.dialoguelnln("Calmus", "The patterns here are a lot larger than any creature markings I've seen here before. "
                + "We'll have to proceed with caution.");
        
        print("The girls nodded, and Calmus continued to lead them through the rocky paths.");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene that plays when the player encounters R.E.S.I. Omega.
     */
    public static void foundOmegaCutscene()
    {
        print("The group finally made it to the top of the volcano. The girls were miserable and sweating bullets. Calmus "
                + "started to pity them/just by seeing their facial expressions.");
        
        MainGame.dialoguelnln("Calmus", "Thank you guys again for joining me; you really didn't have to. We should be able "
                + "to find the\n\tmineral soon.");
        
        MainGame.dialoguelnln("Gaea", "It's (*pant*)... it's all good, Calmus...! We said we would help, so that's what we're "
                + "here for!");
        
        print("Anahita was able to create a light mist over herself and the other girls. Gaea and Ninlil both sighed with "
                + "relief.");
        
        MainGame.dialoguelnln("Ninlil", "Oh, thank you Ana. That helps a lot.");
        
        MainGame.dialoguelnln("Anahita", "No problem! Hopefully it'll be enough to find the mineral and get outta here.");
        
        MainGame.dialoguelnln("Calmus", "I'll search over here then! If you guys can search that area, we should be able to "
                + "find some.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("The group split up to look for the mineral, purple in color. The girls were looking at a cluster of rocks "
                + "under shade,/while Calmus searched elsewhere. Some time passed before they regrouped.");
        
        MainGame.dialoguelnln("Calmus", "Any luck guys?");
        
        MainGame.dialoguelnln("Ninlil", "Unfortunately, no. Nothing we've seen fits the description Lyra gave.");
        
        MainGame.dialoguelnln("Calmus", "I think we can look in a different ar--");
        
        print("Before Calmus finsihed his sentence, a R.E.S.I. Bot flew in from above in front of the group. Dirt, rocks, "
                + "and debris/flew everywhere.");
        
        MainGame.dialoguelnln("Calmus", "Is everyone okay?!");
        
        MainGame.dialoguelnln("Anahita", "I-I think so...");
        
        print("As the dust settled, The R.E.S.I. stared the group down. It had a brilliant red sheen and was well equiped "
                + "with weaponry./It was easily about 3 times the size of the other R.E.S.I Bots the group had faced.");
        
        
        MainGame.dialoguelnln("Calmus", "Wh-whoa! This thing is HUGE!");
        
        MainGame.dialoguelnln("R.E.S.I. Omega", "E N E M Y  F O U N D .  E N G A G I N G  I N  C O M B A T.  "
                + "P R E P A R I N G  W E A P O N R Y .");
        
        MainGame.dialoguelnln("Anahita", "That thing's coming at us! Be careful, guys!!");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene that plays when the player defeats R.E.S.I. Omega.
     */
    public static void defeatedOmegaCutscene()
    {
        MainGame.dialoguelnln("R.E.S.I. Omega", "S Y s T 3 M  F 4 1 L U R 3 ,  5 Y 5 T 3 M  f 4 I L u R 3 .\n\t"
                + "S Y 5 t 3 M 5  4 R e  5 H u t t I N G\n\t"
                + "DO0OO0000OOOOOOO0OOOO0OOOO0O0OOoooooooooooooooooooooo--");
        
        print("After a gruesome fight, the group defeat the R.E.S.I. Bot. As it shut down, some of its parts fell off, and "
                + "its flashing/lights dimmed into darkness. The group celebrated and gave each other high fives for their "
                + "victory.");
        
        MainGame.dialoguelnln("Anahita", "WE DID IT GUYS!");
        
        MainGame.dialoguelnln("Gaea", "We worked so well together! I can't believe we took that thing down!");
        
        MainGame.dialoguelnln("Ninlil", "I... Wow...! We really did it!");
        
        MainGame.dialoguelnln("Calmus", "I'm glad Lyra didn't come here on her own... Thank you guys so much for helping me. "
                + "I couldn't\n\thave done this without you all!");
        
        print("The group continued to cheer for a while, this momentous moment giving them hope that they could potentially "
                + "save Pulchra after all.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("After the cheers subsided, Calmus and Gaea inspected the remains of the Bot while Anahita and Ninlil tried to "
                + "stay cool./Calmus and Gaea looked to see a green liquid seeping from the Bot. Gaea smelled it to see if "
                + "it had an odor.");
        
        MainGame.dialoguelnln("Gaea", "Hey, this green stuff kinda smells like... beans?");
        
        MainGame.dialoguelnln("Calmus", "Really?");
        
        print("Calmus also smelled the mysterious liquid, and sure enough, it smelled just like the beans that are abundantly "
                + "found on/Pulchra.");
        
        MainGame.dialoguelnln("Calmus", "Ana, Ninlil, come see this!");
        
        print("Anahita and Ninlil approached the others to see what they were looking at.");
        
        MainGame.dialoguelnln("Gaea", "You two -- smell this. It smells just like beans!");
        
        print("Both Anahita and Ninlil's faces displayed disgust as they looked at the foul-green color seeping from the "
                + "metal.");
        
        MainGame.dialoguelnln("Gaea", "Oh, grow up! Just trust me on this.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Anahita and Ninlil both smelled the liquid, and their faces lit up.");
        
        MainGame.dialoguelnln("Ninlil", "It really does smell like them... Why could that be?");
        
        MainGame.dialoguelnln("Anahita", "... I think I have an idea. The beans are filled with ethrellium, right? That's "
                + "what gives\n\tus our powers. Maybe the Bots use ethrellium too, and that's how their elements change in "
                + "battle.");
        
        MainGame.dialoguelnln("Calmus", "That actually makes a lot of sense... Good job, Ana! We'll have to be mindful of "
                + "that from\n\there on out then.");
        
        MainGame.dialoguelnln("Gaea", "Hey, there's one more thing here!");
        
        print("Gaea was rummaging through the Bot's remains and found some of the purple mineral they were looking for to "
                + "help Elder Vulca.");
        
        MainGame.dialoguelnln("Calmus", "Oh, you found some! So this thing is what caused the markings. It was collecting "
                + "them all from here.");
        
        MainGame.dialoguelnln("Ninlil", "Could it be to harvest the ethrellium from these as well for future Bots...?");
        
        MainGame.dialoguelnln("Anahita", "Could be! But we have it all now, so let's turn back to help Lyra and the Elder!");
        
        print("The group nodded and turned around to get back to the Infol.");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene that plays when the player returns to Infol and talks to Lyra.
     */
    public static void helpedLyraCutscene()
    {
        MainGame.dialoguelnln("Anahita", "We got the minerals!");
        
        print("The barged into Calmus' house, startling Lyra. The Elder's eyes glimmered with hope after seeing the purple "
                + "mineral.");
        
        MainGame.dialoguelnln("Lyra", "Ah, perfect! I'll be right back; I'll make the medicine!");
        
        print("The group waited while watching the Elder in her bed. When Lyra returned, she had a purple-colored drink "
                + "for Elder Vulca.");
        
        MainGame.dialoguelnln("Lyra", "Okay, Grandma, this won't taste the best, but you should feel better quickly.");
        
        print("Everyone collectively held their breath as they watched Elder Vulca take the drink. After slowly drinking "
                + "it and a/few puckered faces, her eyes were fully open. She then slowly climbed out the bed and hugged "
                + "her grandchildren./Everyone else cheered at the miracle at hand.");
        
        MainGame.dialoguelnln("Elder Vulca", "Thank you, children. I can't imagine getting the mineral was easy, but I am "
                + "so grateful to\n\tyou all. You've all done a great thing today.");
        
        MainGame.dialoguelnln("Calmus", "I'm just... so happy you're okay... I wasn't sure if we'd be able to do anything "
                + "for you.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Calmus started crying. Gaea repaid him by consoling him with a hug as the others comforted him too. Calmus and "
                + "Lyra have a/few years between them in age, and their orphans. Their parents died in an accident years ago, "
                + "so the Elder/took them into her care. Calmus has struggled with this loss all his life. Although he "
                + "suppresses his feelings/this was something that got the better of him. The hug from Gaea helped him "
                + "overcome the/overwhelming emotions he felt.");
        
        MainGame.dialoguelnln("Elder Vulca", "Oh, grandson, it's okay. I'm still here and kicking. I still need to watch over "
                + "you and Lyra.");
        
        MainGame.dialoguelnln("Lyra", "(*sniff*) We just love you so much, Grandma.");
        
        print("Anahita and Ninlil were touched by the moment. As Gaea and Calmus parted, Calmus recollected himself and "
                + "looked at the group.");
        
        MainGame.dialoguelnln("Calmus", "Okay... I think we can go find Frigs now. I just needed to take care of my family.");
        
        MainGame.dialoguelnln("Anahita", "We admire you for that, Calmus. Let's leave whenever you're ready.");
        
        print("The group, Lyra, and the Elder all said their goodbyes before the group departed.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Anahita", "Alright guys, to get to Solice, we'll have to climb Mount Zoni...");
        
        MainGame.dialoguelnln("Gaea", "More. Climbing. Great. Calmus, will you be able to keep us warm? I won't be able to "
                + "stand the cold...");
        
        MainGame.dialoguelnln("Calmus", "I'll do whatever I need to for you guys. You've all done what you could for me.");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene for when the player first gets to Mount Zoni.
     */
    public static void mountZoniCutscene()
    {
        print("As the group arrived to the base of Mount Zoni, Gaea sulked just looking at the size of it and its frozen "
                + "peak./The wind started to pick up, causing her to shiver.");
        
        MainGame.dialoguelnln("Gaea", "This is going to suck...");
        
        MainGame.dialoguelnln("Ninlil", "You'll have to stop complaining eventually, Gaea. Calmus is still here to keep us "
                + "warm.");
        
        MainGame.dialoguelnln("Gaea", "Ugh, I know, but still. I miss the warmth of Degon.");
        
        MainGame.dialoguelnln("Calmus", "I'll do the best I can for you, don't worry. We can stop periodically for breaks and "
                + "to setup camp.");
        
        MainGame.dialoguelnln("Anahita", "Sounds like a plan! Are we all good to go?");
        
        MainGame.dialoguelnln("Gaea", "No... But we might as well start climbing... *again*. I better be ripped at the "
                + "end of this expedition.");
        
        MainGame.promptToEnter();
    }
    
    /**
     * A cutscene that plays when the player first enters Solice
     */
    public static void soliceCutscene()
    {
        print("The group finally made it near the summit of Mount Zoni -- Solice. Everyone was crowded against Calmus "
                + "who was a/human heat generator at this point.");
        
        MainGame.dialoguelnln("Calmus", "Do you guys think you'll be okay to give me space now, or--");
        
        MainGame.dialoguelnln("Anahita, Gaea, and Ninlil", "NO!");
        
        MainGame.dialoguelnln("Calmus", "Okay, okay. Figured I'd just ask.");
        
        print("The cold was bitter and sharp, causing discomfort with every step. THe group scanned the area to see some "
                + "destruction, but/not nearly as much as they've seen before.");
        
        MainGame.dialoguelnln("Anahita", "Well, we might as well look for Frigs. Let's see if anyone has seen him lately.");
    }
    
    public static void elderZenoCutscene()
    {
        MainGame.dialoguelnln("Anahita", "Hello, Elder Zeno!");
        
        print("The group approached Elder Zeno, a tall, slender man with a pointed nose and peppered hair.");
        
        MainGame.dialoguelnln("Elder Zeno", "Ah, hello Anahita!");
        
        print("The two exchanged a brief hug. Anahita could come to visit Frigs if she had extra time in the year, so she "
                + "was well/acquainted with the Elder.");
        
        MainGame.dialoguelnln("Elder Zeno", "And a warm -- er, cold -- hello to the rest of you too.");
        
        print("Gaea rolled her eyes at the remark but smiled afterward.");
        
        print("Solice, just like the other towns that were visited, were destroyed. The population here is "
                + "normally/a lot less the other towns, but the invasion has decreased it even more. Barely any one was "
                + "there/when the group arrived.");
        
        MainGame.dialoguelnln("Ninlil", "It's... so desolate here. It's so depressing to see...");
        
        MainGame.dialoguelnln("Elder Zeno", "I know. It's been difficult all around, I'm sure, but the few of us here are "
                + "holding our\n\town. I digress. What are you all doing here?");
        
        MainGame.dialoguelnln("Anahita", "We were looking for Frigs. Do you have any idea of where he is?");
        
        MainGame.dialoguelnln("Elder Zeno", "Hm... I haven't seen him in a while, actually. I would recommend you check the "
                + "summit of\n\tMount Zoni. I know that he goes there occasionally. He... he lost his brother during the "
                + "invasion.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Everyone in the group felt their heart sink as they heard the news. They could all relate. Despite them all "
                + "losing/someone, hearing someone else go through that pain was still saddening.");
        
        MainGame.dialoguelnln("Anahita", "I see... We'll see if we can find and hopefully help him too.");
        
        MainGame.dialoguelnln("Elder Zeno", "Please -- do whatever you can for the boy if you find him. There's no point in "
                + "being alone.\n\tEspecially now.");
        
        MainGame.dialoguelnln("Anahita", "Will do, Elder. Thank you.");
        
        print("The group started to get ready to trek higher in the mountain. All the girls clung to Calmus again for heat, "
                + "and he sighed.");
        
        MainGame.dialoguelnln("Calmus", "Here we go again...");
        
        MainGame.promptToEnter();
    }
    
    public static void foundFrigsCutscene()
    {
        print("The girls were still huddled around Calmus as they ascended to the peak of the mountain. Shivering, red in the "
                + "face, and cold,/the group rejoiced at their accomplishment. They then saw Frigs not too far off and "
                + "approached him.");
        
        print("The girls painstakingly separated from Calmus so that they could all address Frigs properly. Frigs was "
                + "standing, looking/far off at the bottom of the mountain and cloudy sky.");
        
        MainGame.dialoguelnln("Gaea", "Hey, Frigs?");
        
        print("He turned around to reveal frozen snot and tears on his face. He quickly brushed it all off and gave a slight "
                + "smile before\n\tanother tear started to run down his cheek.");
        
        MainGame.dialoguelnln("Frigs", "You could've knocked first. Can't you see I'm not doing too hot right now?");
        
        MainGame.dialoguelnln("Gaea", "We know, Frigs, and it's okay. We're here for you.");
        
        MainGame.dialoguelnln("Frigs", "And my brother said the same. Look at where he's at now.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Frigs' voice cracked slightly. Ninlil and Gaea looked at each other and nodded slightly. Ninlil had hope for "
                + "Frigs. She/knew exactly how he felt and wanted him to console him.");
        
        MainGame.dialoguelnln("Ninlil", "I know how you feel Frigs. It really isn't an easy thing, but it will be okay! "
                + "I lost\n\tsomeone too recently...");
        
        MainGame.dialoguelnln("Frigs", "But where they the only family that you had left? Were they the person you looked up "
                + "to most?\n\tWhere they the only one you knew you could depend on no matter what?");
        
        MainGame.dialoguelnln("Ninlil", "Frigs, I...");
        
        print("The groups hearts were collectively devastated. Frigs and his brother, Verg, were orphans. They had no "
                + "extended family,/so they took care of each other. The loss made him feel *truly* alone.");
        
        MainGame.dialoguelnln("Frigs", "there's simply no one left in my family now... i'm the only one that's left. do you "
                + "know how that feels??");
        
        MainGame.dialoguelnln("Calmus", "Frigs, I know. It's terrible... But know that you aren't really alone. You still "
                + "have people\n\there that care for you.");
        
        MainGame.dialoguelnln("Frigs", "Calmus, the difference between you and me is that you at least have your grandmother, "
                + "an aunt.\n\tI have *nobody*. They... they're all gone.");
        
        print("Frigs wiped the now frozen tear from his cheek as more started to stream down. Anahita's heart was broken for "
                + "her dear friend./Before she could speak, Frigs did.");
        
        MainGame.dialoguelnln("Frigs", "How about a little challenge? I've beens sitting here too long anyway, haha.");
        
        print("Frigs was trying to make himself laugh to cope with his pain. That was always how he coped with things since "
                + "he never/learned how to confront his emotions healthily.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Ninlil saw a piece of herself reflected in Frigs. The way he wanted to challenge the group despite his current "
                + "state was/just how she acted. She hoped that he would be able to overcome this pain.");
        
        MainGame.dialoguelnln("Ninlil", "You know what, sure thing, Frigs. I'll accept your challenge.");
        
        MainGame.dialoguelnln("Anahita", "Be careful you two!");
        
        MainGame.dialoguelnln("Frigs", "... thank you.");
        
        MainGame.promptToEnter();
    }
    
    public static void defeatedFrigsCutscene()
    {
        print("Ninlil and Frigs were both panting from the fight, Ninlil the victor. Frigs looked at the sky and smiled, the "
                + "cold winds/making his hair blow around. More tears came.");
        
        MainGame.dialoguelnln("Frigs", "I miss you so much, Verg...");
        
        print("Anahita rushed over to Frigs and embraced him in a hug. Despite the cold, he was slightly warm. After a few "
                + "seconds,/Frigs hugged her back, making Gaea smile.");
        
        print("Anahita looked at her friend. There was sadness in his eyes, and she wished it all away from him.");
        
        MainGame.dialoguelnln("Anahita", "We are here for you, Frigs. Verg may be gone, but he was very proud of you and loved "
                + "you so much.\n\tHe would want you to keep pushing on and not dwell too much on the past.");
        
        print("Frigs chuckled slightly at the thought of his brother.");
        
        MainGame.dialoguelnln("Frigs", "Yeah... yeah, you're right. I guess I'll have to move on from here...");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Calmus came by and put a hand on Frigs' shoulder.");
        
        MainGame.dialoguelnln("Calmus", "We love you Frigs. Depsite him being gone, 'there is a friend that sticks closer "
                + "than a\n\tbrother.' In this case, we are -- and always will be -- your brothers and sisters.");
        
        print("Anahtia, Gaea, and Ninlil all smiled at the comment because it was true. The group, despite the distance from "
                + "where they/lived, and any conflict they may have had, they've all grown -- individually and together. There "
                + "was a/great strength in their unity.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Frigs smiled and felt his heart slowly mend back to being whole. He realized that he will never be alone, and "
                + "that brought/him peace.");
        
        MainGame.dialoguelnln("Frigs", "Thank you all... The words you've all said... they're true, and I'm thankful you've "
                + "shown me this.");
        
        MainGame.dialoguelnln("Calmus", "Anytime, Frigs. You will always have our support.");
        
        MainGame.dialoguelnln("Ninlil", "It would be great if you were to join us, too. We'd love to have your strength "
                + "with us.");
        
        print("The group informed Frigs of their goals and how close they are to accomplishing them if he joins.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Frigs", "Of course I'm in. After all that's happened, we need restoration. I'll be with you "
                + "all through and through.");
        
        print("The group cheered at the news.");
        
        MainGame.dialoguelnln("Gaea", "Before we talk any longer, can we PLEASE leave this mountain? I cannot feel my legs "
                + "at all!");
        
        print("Anahita and Ninlil nodded vigorously in agreement while Calmus and Frigs both laughed.");
        
        MainGame.dialoguelnln("Frigs", "Come on, it's not *that* cold. You'll get used to it.");
        
        print("The girls all scoffed and groaned as they huddled around Calmus and forced him to start marching them down "
                + "the mountain./Frigs laughed as he walked next to them. Anahita shot water at him to keep him quiet the "
                + "rest of the way.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter(); 
        
        print("At the bottom of Mount Zoni, Gaea addressed the entire group.");
        
        MainGame.dialoguelnln("Gaea", "Before we go to Zoni, I would like to stop by the Elerric. Since Fultra's "
                + "passing,\n\tI haven't heard word from his grandmother and how she's doing. Can we make that stop first? "
                + "This\n\tshould be the last thing, I promise.");
        
        MainGame.dialoguelnln("Anahita", "Yeah, that's fine! It's the only town we haven't been to yet, so we might as "
                + "well check\n\ton them. I know that it's past the Forlorn Cave, so we'll need to get through there first.");
        
        MainGame.dialoguelnln("Frigs", "No issue here. Let's do it.");
        
        MainGame.dialoguelnln("Gaea", "Thank you so much guys. (*sigh*) Fultra...");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Congratulations! Frigs joined your party!");
        
        MainGame.promptToEnter();
    }
    
    public static void forlornCaveCutscene()
    {
        print("The group came upon the Forlorn Cave, and Calmus had a small flame ready to be a light for the group."
                + "The/entrance of the cave was brooding and cold. An air of sorrowfulness "
                + "started to wash/over the group as they came closer to the cave.");
        
        MainGame.dialoguelnln("Anahita", "oh... wow... i feel so... defeated and... dejected all of a sudden...");
        
        MainGame.dialoguelnln("Gaea", "oh, yeah... i forgot that the cave does this... i know the quickest way "
                + "out\n\tthough... just follow me and... we'll be... alright...");
        
        print("The group looked at Gaea and nodded as they entered the cave.");
        
        print("The Forlorn Cave is the strangest place in all of Pulchra. Those that do not know the path to Elerric "
                + "or/out of it to a different area are often never heard from again. The cave exudes an air of "
                + "dejection,/so much so that those that enter and get lost lose hope of ever finding their way out.");
        
        print("Those that live in the Elerric train here to learn how to overcome their emotions. This is why "
                + "these/Pulchrians are known to be more formidable. As they train here, they learn to overcome the "
                + "sorrowfullness and find their way out.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("Gaea confidently lead the group through the cave, determined to get to Elerric. When with "
                + "Fultra,/he would show her the way in and out, so she knew it like the back of her hand. She still "
                + "struggles/with the emotions the cave weighs on her, but her determination was much stronger, allowing her "
                + "guide the/rest of the group. They had come too far to lose hope now.");
        
        print("As the group continued to march on, Gaea mumbled to herself.");
        
        MainGame.dialoguelnln("Gaea", "we can't... stop now... need to keep... pushing through... for fultra...");
        
        MainGame.promptToEnter();
    }
    
    public static void elerricCutscene()
    {
        print("The group trudged through the cave the best they could, the sense of hopelessness slowly consuming them more "
                + "and/more. They passed by a few remains of those that got lost in the cave, and it caused the weight "
                + "of their emotions to increase. Ninlil eventaully cracked and fell to the cold, cave floor.");
        
        MainGame.dialoguelnln("Ninlil", "i can't keep going... please go without me... i'll only... hold you all back...");
        
        print("Gaea started to panic slightly as she saw Ninlil on the brink of giving up.");
        
        MainGame.dialoguelnln("Gaea", "no, no... you can't stop here... ninlil... we're so close to the... exit...");
        
        MainGame.dialoguelnln("Ninlil", "gaea, please...");
        
        print("Gaea was starting to feel tempted to stop, too, but she still held onto hope. She decided to pull Ninlil up "
                + "on her/feet and to drag her the rest of the way.");
        
        MainGame.dialoguelnln("Gaea", "look... there's a light at the end of this path...! we've come too far to... "
                + "stop now... i'm not giving up on... any of you...");
        
        print("With Gaea dragging a hopeless Ninlil who was close to tears, the group fought their emotional battles as they "
                + "made it/to the end of the cave.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        print("The group made it to the end of the cave and emerged into the daylight and Elerric. Everyone felt relieved "
                + "from/the weight of their emotions. Ninlil, who had now regained her sense, hugged Gaea very tightly, "
                + "something Ninlil rarely ever did. The group and Gaea were shocked, including Ninlil herself, but she felt "
                + "it was right. Gaea hugged her back.");
        
        MainGame.dialoguelnln("Ninlil", "Thank you so much, Gaea. If you didn't drag me out of there...");
        
        MainGame.dialoguelnln("Gaea", "It's alright, Ninlil. We're all here to support each other.");
        
        print("After the two separated, the group went deeper into Elerric to see that it was utterly destroyed. The group "
                + "gasped at the damage done to the town. There were scortch markings everywhere, things were burned, and "
                + "houses were destroyed.");
        
        MainGame.dialoguelnln("Frigs", "What on Earth happened here?? There's no way the Bots did all of this, did they??");
        
        MainGame.dialoguelnln("Calmus", "There's no good telling of what they did. These burns markings are crazy!");
        
        MainGame.dialoguelnln("Anahita", "Let's go look for Elder Clairdra then -- quickly! Who knows if she may need help!");
        
        MainGame.promptToEnter();
    }
    
    public static void elderClairdraCutscene()
    {
        print("As the group ran through the town, they saw people cleaning up some debris that was left on the ground. Some "
                + "pieces/were completely burnt, and some things crumbled into dust once they were touched. Eventally, the "
                + "group found/Elder Clairdra. She was standing alone looking at the sky.");
        
        MainGame.dialoguelnln("Gaea", "Oh, Elder Clairdra! You're okay!");
        
        print("Before the Elder could react, Gaea had pulled her in for a hug. Once the Elder realized who it was, she "
                + "hugged back.");
        
        MainGame.dialoguelnln("Elder Clairdra", "Oho! It's good to see you, Gaea! The rest of you all, too. You all made it "
                + "through\n\tthe cave safely! That's wonderful to see. I assume you lead everyone here, Gaea.");
        
        MainGame.dialoguelnln("Gaea", "Yes, I did! I remembered the way, thankfully. It was difficult, but we overcame it.");
        
        print("Ninlil started to look at the ground, thinking about the cave again.");
        
        MainGame.dialoguelnln("Elder Clairdra", "Well, it's a good thing you're all safe.");
        
        MainGame.dialoguelnln("Gaea", "Yes. Are you all safe though? What *happened* here?");
        
        MainGame.dialoguelnln("Anahita", "The damage done here is the worst out of all the other towns!");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Elder Clairdra", "Ah, I might as well explain everything that happened. During the invasion,"
                + "\n\t we, along with the other towns, were attacked by the R.E.S.I. Bots. As we tried to recover from the "
                + "first\n\tattack, there was another, lone Bot that came in. It was... different. It was faster and "
                + "stronger\n\tthan the other ones. It blasted everything with electricity, which was surprising to see.");
        
        print("Ninlil perked up by hearing the electricity part.");
        
        MainGame.dialoguelnln("Ninlil", "Elder, was this Bot sleek in design, or was it more humanoid...");
        
        MainGame.dialoguelnln("Elder Clairdra", "Hmm, I believe it was more humanoid. It was difficult to tell as it "
                + "attacked at night.");
        
        MainGame.dialoguelnln("Frigs", "Ninlil, are you insinuating something...?");
        
        print("Ninlil slowly started to nod her head. As she did, Gaea's eyes widened.");
        
        MainGame.dialoguelnln("Gaea", "You don't mean that...");
        
        MainGame.dialoguelnln("Ninlil", "Unless the Bots are getting better with using more elemental based attacks, there's "
                + "the\n\tpotential that it could've been Fultra...");
        
        print("Both Elder Clairdra and Gaea gasped at the thought. Elder Clairdra was already aware of Fultra's death. A "
                + "survivor/from the invasion in Zoni City returned back to Elerric and informed her of what happened to him. "
                + "She mourned/for her grandson that day, but still had her duties as Elder to take care of, so she had to "
                + "collect herself/quickly.. Hearing that the other attacker might have been her grandson filled her with "
                + "hope and confusion. Gaea, however, was hot with emotion.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Gaea", "Ninlil! You know he's dead! Don't spread false hope like that. What's wrong with you?");
        
        MainGame.dialoguelnln("Ninlil", "Gaea, that's not my intention at all, I--");
        
        MainGame.dialoguelnln("Gaea", "No! Accept the fact that he's *dead*! He's gone!");
        
        print("Gaea's face became red and hot with emotion, and Ninlil stared sadly at the ground. "
                + "She meant no harm -- she just wanted to throw out possible solutions -- and now felt awful. She thought "
                + "she was getting along better with Gaea.");
        
        print("Anahita pulled Gaea aside to talk to her privately.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Anahita", "Gaea, what's going on? There was no reason to blow up on Ninlil like that.");
        
        MainGame.dialoguelnln("Gaea", "And there was no reason for her to say Fultra could still be alive. I *know* what I "
                + "saw\n\t-- he's *dead*.");
        
        MainGame.dialoguelnln("Anahita", "Even if she's right -- and I'm not saying she is -- wouldn't that be some form of "
                + "good news?\n\tIt would mean that he's still alive!");
        
        MainGame.dialoguelnln("Gaea", "Alive and attacking his own home?? That wouldn't make sense! It can't be him... It "
                + "\n\tcan't be...");
        
        print("Anahita consoled Gaea a little more, which helped her calm down. Gaea eventaully came to her senses and "
                + "acknowledged/how rashly she acted. The two joined the rest of the group and the Elder.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Gaea", "Ninlil, I'm sorry. I shouldn't have snapped at you like that. Thinking about him still "
                + "being a live is... a touchy subject still.");
        
        MainGame.dialoguelnln("Ninlil", "It's okay -- I understand.");
        
        print("The two quickly reconciled and everyone turned their attention back to the Elder.");
        
        MainGame.dialoguelnln("Elder Clairdra", "I understand that it's a sensitive subject, Gaea. It's confusing for me too, "
                + "but\n\tdon't be blindsighted. Regardless of if it was him or not, you all need to be careful. That Bot "
                + "was strong.");
        
        MainGame.dialoguelnln("Anahita", "Understood. Is there anything we can do you here, Elder?");
        
        MainGame.dialoguelnln("Calmus", "If we can help, we will. We've seen too much destruction lately.");
        
        MainGame.dialoguelnln("Elder Clairdra", "Thank you all, but no. You all have a much greater mission to attend to. "
                + "We\n\tknow how to clean up the damage done here. You go to Zoni City instead and fight for us all. I've "
                + "seen you\n\tall grow, and I'm sure you're more than capable of bringing peace back. Go! We'll be here for "
                + "you\n\tafterwards.");
        
        MainGame.dialoguelnln("Gaea", "Thank you, Elder... We'll do what we can. For you, for Fultra, and for Pulchra.");
        
        print("The group gave the Elder their best wishes as they prepared to depart from Elerric.");
        
        //---------------------------------------------------------------------------------------------------------------------
        MainGame.promptToEnter();
        
        MainGame.dialoguelnln("Anahita", "So... that's everythingt then, right?");
        
        MainGame.dialoguelnln("Gaea", "I believe so. There's not much else I need or want to do.");
        
        MainGame.dialoguelnln("Calmus", "Our next step is Zoni City then, right?");
        
        MainGame.dialoguelnln("Ninlil", "Seems like it. It's time to go back to where it all started...");
        
        MainGame.dialoguelnln("Frigs", "Let's go, then. It's about time we fight Irwin.");
        
        MainGame.dialoguelnln("Anahita", "Agreed. We've all grown a lot together. Let's be wise in how we handle everything. "
                + "This\n\tis it guys!");
        
        MainGame.dialoguelnln("Gaea", "Let's do this! For Pulchra!");
    }
}
