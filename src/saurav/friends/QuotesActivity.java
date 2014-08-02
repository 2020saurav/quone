package saurav.friends;

import java.util.ArrayList;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;



import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuotesActivity extends Activity implements OnClickListener {
	
	private String []th = new String[245];
{
	th[0]="Just know, when you truly want success, you\'ll never give up on it. No matter how bad the situation may get.";
    th[1]="Accept responsibility for your life. Know that it is you who will get you where you want to go, no one else.";
    th[2]="I don\'t regret the things I\'ve done, I regret the things I didn\'t do when I had the chance.";
    th[3]="Challenges are what make life interesting and overcoming them is what makes life meaningful.";
    th[4]="Its hard to wait around for something you know might never happen, but its harder to give up when you know its everything you want.";
    
    th[5]="One of the most important keys to Success is having the discipline to do what you know you should do, even when you dont feel like doing it.";
    th[6]="Good things come to those who wait.. greater things come to those who get off their ass and do anything to make it happen.";
    th[7]="Happiness cannot be traveled to, owned, earned, or worn. It is the spiritual experience of living every minute with love, grace & gratitude.";
    th[8]="In order to succeed, your desire for success should be greater than your fear of failure.";

    th[9]="Go where you are celebrated - not tolerated. If they can\'t see the real value of you, it\'s time for a new start.";
    th[10]="Dont be afraid to stand for what you believe in, even if that means standing alone.";

    th[11]="The best revenge is massive success.";
    th[12]= "Forget all the reasons it won\'t work and believe the one reason that it will";
    th[13]="The only way to do great work is to love what you do. If you haven\'t found it yet, keep looking. Don\'t settle.";
    th[14]="Life is short, live it. Love is rare, grab it. Anger is bad, dump it. Fear is awful, face it. Memories are sweet, cherish it.";
    th[15]="When you say \"It\'s hard\", it actually means \"I\'m not strong enough to fight for it\". Stop saying its hard. Think positive!";
    th[16]="Life is like photography. You need the negatives to develop.";
    th[17]="Don\'t worry about failures, worry about the chances you miss when you don\'t even try.";
    
    th[18]="The pain you feel today is the strength you feel tomorrow. For every challenge encountered there is opportunity for growth.";
    th[19]="Build your own dreams, or someone else will hire you to build theirs.";
    th[20]="The only thing that stands between you and your dream is the will to try and the belief that it is actually possible.";
    th[21]="Self confidence is the most attractive quality a person can have. How can anyone see how awesome you are if you can\'t see it yourself?";
    
    th[22]="We learn something from everyone who passes through our lives.. Some lessons are painful, some are painless.. but, all are priceless.";
    th[23]="Being happy doesn\'t mean that everything is perfect. it means that you\'ve decided to look beyond the imperfections";
    th[24]="Nobody ever wrote down a plan to be broke, fat, lazy, or stupid. Those things are what happen when you don\'t have a plan";

    th[25]="Three things you cannot recover in life: the WORD after it\'s said, the MOMENT after it\'s missed and the TIME after it\'s gone. Be Careful!";
    th[26]="Though no one can go back and make a brand new start, anyone can start from now and make a brand new ending.";
    th[27]="Walk away from anything or anyone who takes away from your joy. Life is too short to put up with fools.";
    th[28]="Love what you have. Need what you want. Accept what you receive. Give what you can. Always remember, what goes around, comes around...";
    th[29]="Just remember there is someone out there that is more than happy with less than what you have.";
    th[30]="The biggest failure you can have in life is making the mistake of never trying at all.";
    th[31]="No one is going to hand me success. I must go out and get it myself. That\'s why I\'m here. To dominate. To conquer. Both the world, and myself";
    th[32]="Whatever the mind of man can conceive and believe, it can achieve. \n -Napoleon Hill";
    
    th[33]="Strive not to be a success, but rather to be of value. \n -Albert Einstein";
    th[34]="Two roads diverged in a wood, and I took the one less traveled by, And that has made all the difference. \n -Robert Frost";
    th[35]="I attribute my success to this: I never gave or took any excuse.\n -Florence Nightingale";
    th[36]="You miss 100% of the shots you don\'t take. \n -Wayne Gretzky";
    th[37]="I can teach anybody how to get what they want out of life. The problem is that I can\'t find anybody who can tell me what they want. \n - Mark Twain";
    th[38]="The most difficult thing is the decision to act, the rest is merely tenacity. \n -Amelia Earhart";
    th[39]="Every strike brings me closer to the next home run. \n -Babe Ruth";
    th[40]="Definiteness of purpose is the starting point of all achievement. \n -W. Clement Stone";
    th[41]="The past is a ghost, the future a dream. All we ever have is now. \n -Bill Cosby";
    th[42]="Life is what happens to you while you\'re busy making other plans. \n -John Lennon";
    th[43]="We become what we think about. \n -Earl Nightingale";
    
    th[44]="I went to the woods because I wished to live deliberately, to front only the essential facts of life, and see if I could not learn what it had to teach, and not, when I came to die, discover that I had not lived.\n - Henry David Thoreau";
    th[45]="Life is 10% what happens to me and 90% of how I react to it. \n -Charles Swindoll";
    th[46]="The most common way people give up their power is by thinking they don\'t have any. \n -Alice Walker";
    th[47]="The mind is everything. What you think you become.  \n -Buddha";
    th[48]="The best time to plant a tree was 20 years ago. The second best time is now. \n -Chinese Proverb";
    th[49]="An unexamined life is not worth living. \n -Socrates";
    th[50]="Eighty percent of success is showing up. \n -Woody Allen";
    th[51]="Your time is limited, so don\'t waste it living someone else\'s life. \n -Steve Jobs";
    th[52]="Winning isn\'t everything, but wanting to win is. \n -Vince Lombardi";
    th[53]="I am not a product of my circumstances. I am a product of my decisions. \n -Stephen Covey";
    th[54]="Every child is an artist.  The problem is how to remain an artist once he grows up. \n -Pablo Picasso";
    
    th[55]="You can never cross the ocean until you have the courage to lose sight of the shore. \n -Christopher Columbus";
    th[56]="I\'ve learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel. \n -Maya Angelou";
    th[57]="Either you run the day, or the day runs you. \n -Jim Rohn";
    th[58]="Whether you think you can or you think you can\'t, you\'re right. \n -Henry Ford";
    
    th[59]="The two most important days in your life are the day you are born and the day you find out why. \n -Mark Twain";
    th[60]="Whatever you can do, or dream you can, begin it.  Boldness has genius, power and magic in it. \n -Johann Wolfgang von Goethe";
    th[61]="The best revenge is massive success. \n -Frank Sinatra";
    th[62]="People often say that motivation doesn\'t last. Well, neither does bathing.  That\'s why we recommend it daily. \n -Zig Ziglar";
    th[63]="Life shrinks or expands in proportion to one\'s courage. \n -Anais Nin";
    th[64]="If you hear a voice within you say \'you cannot paint,\' then by all means paint and that voice will be silenced. \n -Vincent Van Gogh";
    
    th[65]="There is only one way to avoid criticism: do nothing, say nothing, and be nothing. \n -Aristotle";
    th[66]="Ask and it will be given to you; search, and you will find; knock and the door will be opened for you. \n -Jesus";
    th[67]="The only person you are destined to become is the person you decide to be. \n -Ralph Waldo Emerson";
    th[68]="Go confidently in the direction of your dreams.  Live the life you have imagined. \n -Henry David Thoreau";
    th[69]="When I stand before God at the end of my life, I would hope that I would not have a single bit of talent left and could say, I used everything you gave me. \n -Erma Bombeck";
    th[70]="Few things can help an individual more than to place responsibility on him, and to let him know that you trust him.  \n -Booker T. Washington";
    th[71]="Certain things catch your eye, but pursue only those that capture the heart. \n - Ancient Indian Proverb";
    th[72]="Believe you can and you\'re halfway there. \n -Theodore Roosevelt";
    th[73]="Everything you\'ve ever wanted is on the other side of fear. \n -George Addair";
    th[74]="We can easily forgive a child who is afraid of the dark; the real tragedy of life is when men are afraid of the light. \n -Plato";
    th[75]="Teach thy tongue to say, \"I do not know,\" and thous shalt progress. \n -Maimonides";
    th[76]="Start where you are. Use what you have.  Do what you can. \n -Arthur Ashe";
    th[77]= th[73];
    th[78]="Fall seven times and stand up eight. \n -Japanese Proverb";
    th[79]="When one door of happiness closes, another opens, but often we look so long at the closed door that we do not see the one that has been opened for us. \n -Helen Keller";
    
    th[80]="Everything has beauty, but not everyone can see. \n -Confucius";
    th[81]="How wonderful it is that nobody need wait a single moment before starting to improve the world. \n -Anne Frank";
    th[82]="When I let go of what I am, I become what I might be. \n -Lao Tzu";
    th[83]="Life is not measured by the number of breaths we take, but by the moments that take our breath away. \n -Maya Angelou";
    th[84]="Happiness is not something readymade.  It comes from your own actions. \n -Dalai Lama";
    th[85]="If you\'re offered a seat on a rocket ship, don\'t ask what seat! Just get on. \n -Sheryl Sandberg";
    
    th[86]= th[30];
    th[87]="If the wind will not serve, take to the oars. \n -Latin Proverb";
    th[88]="You can\'t fall if you don\'t climb.  But there\'s no joy in living your whole life on the ground. \n -Unknown";
    th[89]="We must believe that we are gifted for something, and that this thing, at whatever cost, must be attained. \n -Marie Curie";
    th[90]="Too many of us are not living our dreams because we are living our fears. \n -Les Brown";
    th[91]="Challenges are what make life interesting and overcoming them is what makes life meaningful. \n -Joshua J. Marine";
    th[92]="If you want to lift yourself up, lift up someone else. \n -Booker T. Washington";
    
    th[93]="I have been impressed with the urgency of doing. Knowing is not enough; we must apply. Being willing is not enough; we must do. \n -Leonardo da Vinci";
    th[94]="Limitations live only in our minds.  But if we use our imaginations, our possibilities become limitless. \n -Jamie Paolinetti";
    th[95]="You take your life in your own hands, and what happens? A terrible thing, no one to blame. \n -Erica Jong";
    th[96]="What\'s money? A man is a success if he gets up in the morning and goes to bed at night and in between does what he wants to do. \n -Bob Dylan";
    th[97]="I didn\'t fail the test. I just found 100 ways to do it wrong. \n -Benjamin Franklin";
    th[98]="In order to succeed, your desire for success should be greater than your fear of failure. \n -Bill Cosby";
    th[99]="A person who never made a mistake never tried anything new. \n - Albert Einstein";
    th[100]="The person who says it cannot be done should not interrupt the person who is doing it. \n -Chinese Proverb";
    
    th[101]="There are no traffic jams along the extra mile. \n -Roger Staubach";
    th[102]="It is never too late to be what you might have been. \n -George Eliot";
    th[103]="You become what you believe. \n -Oprah Winfrey";
    th[104]="I would rather die of passion than of boredom. \n -Vincent van Gogh";
    th[105]="A truly rich man is one whose children run into his arms when his hands are empty. \n -Unknown";
    th[106]="It is not what you do for your children, but what you have taught them to do for themselves, that will make them successful human beings.  \n -Ann Landers";
    th[107]="If you want your children to turn out well, spend twice as much time with them, and half as much money. \n -Abigail Van Buren";
    
    th[108]="Build your own dreams, or someone else will hire you to build theirs. \n -Farrah Gray";
    th[109]="The battles that count aren\'t the ones for gold medals. The struggles within yourself \n - the invisible battles inside all of us \n - that\'s where it\'s at. \n -Jesse Owens";
    th[110]="Education costs money.  But then so does ignorance. \n -Sir Claus Moser";
    th[111]="I have learned over the years that when one\'s mind is made up, this diminishes fear. \n -Rosa Parks";
    th[112]="It does not matter how slowly you go as long as you do not stop. \n -Confucius";
    th[113]="If you look at what you have in life, you\'ll always have more. If you look at what you don\'t have in life, you\'ll never have enough. \n -Oprah Winfrey";
    th[114]="Remember that not getting what you want is sometimes a wonderful stroke of luck. \n -Dalai Lama";
    
    th[115]="You can\'t use up creativity.  The more you use, the more you have. \n -Maya Angelou";
    th[116]="Dream big and dare to fail. \n -Norman Vaughan";
    th[117]="Our lives begin to end the day we become silent about things that matter. \n -Martin Luther King Jr.";
    th[118]="Do what you can, where you are, with what you have. \n -Teddy Roosevelt";
    th[119]="If you do what you\'ve always done, you\'ll get what you\'ve always gotten. \n -Tony Robbins";
    th[120]="Dreaming, after all, is a form of planning. \n -Gloria Steinem";
    th[121]="It\'s your place in the world; it\'s your life. Go on and do all you can with it, and make it the life you want to live. \n -Mae Jemison";
    th[122]="You may be disappointed if you fail, but you are doomed if you don\'t try. \n -Beverly Sills";
    th[123]="Remember no one can make you feel inferior without your consent. \n -Eleanor Roosevelt";
    th[124]="Life is what we make it, always has been, always will be. \n -Grandma Moses";
    
    th[125]="The question isn\'t who is going to let me; it\'s who is going to stop me. \n -Ayn Rand";
    th[126]="When everything seems to be going against you, remember that the airplane takes off against the wind, not with it. \n -Henry Ford";
    th[127]="It\'s not the years in your life that count. It\'s the life in your years. \n -Abraham Lincoln";
    th[128]="Change your thoughts and you change your world. \n -Norman Vincent Peale";
    th[129]="Either write something worth reading or do something worth writing. \n -Benjamin Franklin";
    th[130]="Nothing is impossible, the word itself says, \"I\'m possible!\" \n -Audrey Hepburn";
    th[131]="The only way to do great work is to love what you do. \n -Steve Jobs";
    th[132]="If you can dream it, you can achieve it. \n -Zig Ziglar";
    
    th[133]="There is no satisfaction that can compare with looking back across the years and finding you\'ve grown in self-control, judgment, generosity, and unselfishness.\n - Ella Wheeler Wilcox";
    th[134]="People often say that this or that person has not yet found himself. But the self is not something one finds, it is something one creates.\n - Thomas Szasz";
    th[135]="It is the individual who knows how little they know about themselves who stands the most reasonable chance of finding out something about themselves before they die.\n - S. I. Hayakawa";
    th[136]="If you don\'t get lost, there\'s a chance you may never be found.\n - Anonymous";
    th[137]="There is only one corner of the universe you can be certain of improving, and that\'s your own self.\n - Aldous Huxley";
    th[138]="The greatest explorer on this earth never takes voyages as long as those of the man who descends to the depth of his heart.\n - Julien Green";
    th[139]="I think somehow we learn who we really are and then we live with that decision.\n - Eleanor Roosevelt";
    th[140]="Why should we honour those that die upon the field of battle? A man may show as reckless a courage in entering into the abyss of himself.\n - William Butler Yeats";
    th[141]="For most men life is a search for the proper manila envelope in which to get themselves filed.\n - Clifton Fadiman";
    th[142]="The searching-out and thorough investigation of truth ought to be the primary study of man.\n - Cicero";
    th[143]="I know well what I am fleeing from but not what I am in search of.\n - Michel de Montaigne";
    th[144]="There are three things extremely hard: steel, a diamond, and to know one\'s self.\n - Benjamin Franklin";
    th[145]="You cannot dream yourself into a character; you must hammer and forge yourself one.\n - James A. Froude";
    th[146]="A man travels the world over in search of what he needs and returns home to find it.\n - George Moore";
    th[147]="Men go abroad to wonder at the heights of mountains, at the huge waves of the sea, at the long courses of the rivers, at the vast compass of the ocean, at the circular motions of the stars, and they pass by themselves without wondering.\n - St. Augustine";
    th[148]="We only become what we are by the radical and deep-seated refusal of that which others have made of us.\n - Jean-Paul Sartre";
    th[149]="Not until we are lost do we begin to understand ourselves.\n - Henry David Thoreau";
    th[150]="A man who finds no satisfaction in himself will seek for it in vain elsewhere.\n - La Rochefoucauld";
    th[151]="There are things which a man is afraid to tell even to himself, and every decent man has a number of such things stored away in his mind.\n - Fyodor Dostoyevsky";
    th[152]="Becoming conscious is of course a sacrilege against nature; it is as though you had robbed the unconscious of something.\n - Carl G. Jung";
    th[153]="The well bred contradict other people. The wise contradict themselves.\n - Oscar Wilde";
    th[154]="I may not have gone where I intended to go, but I think I have ended up where I intended to be.\n - Douglas Adams";
    th[155]="Know yourself. Don\'t accept your dog\'s admiration as conclusive evidence that you are wonderful.\n - Ann Landers";
    th[156]="\'Know thyself?\' If I knew myself, I\'d run away.\n - Johann Wolfgang von Goethe";
    th[157]="Just when I think I have learned the way to live, life changes.\n - Hugh Prather";
    th[158]="You will recognize your own path when you come upon it, because you will suddenly have all the energy and imagination you will ever need.\n - Jerry Gillies";
    th[159]="A person often meets his destiny on the road he took to avoid it.\n - Jean de La Fontaine";
    th[160]="Man never knows what he wants; he aspires to penetrate mysteries and as soon as he has, he wants to reestablish them. Ignorance irritates him and knowledge cloys.\n - Henri Frederic Amiel";
    th[161]=th[22];
    th[162]="One must know oneself. If this does not serve to discover truth, it at least serves as a rule of life and there is nothing better.\n - Blaise Pascal";
    th[163]="If you resist reading what you disagree with, how will you ever acquire deeper insights into what you believe? The things most worth reading are precisely those that challenge our convictions.\n - Anonymous";
    th[164]="It is not only the most difficult thing to know oneself, but the most inconvenient one, too.\n - H.W. Shaw";
    th[165]="Painting is self-discovery. Every good artist paints what he is.\n - Jackson Pollock";
    th[166]="One\'s own self is well hidden from one\'s own self; of all mines of treasure, one\'s own is the last to be dug up.\n - Friedrich Wilhelm Nietzsche";
    th[167]="One was a book I read by Mahatma Gandhi. In it was a passage where he said that religion, the pursuing of the inner journey, should not be separated from the pursuing of the outer and social journey, because we are not isolated beings.\n - Satish Kumar";
    th[168]="Your work is to discover your world and then with all your heart give yourself to it.\n - Buddha";
    th[169]="All wonders you seek are within yourself.\n - Sir Thomas Browne";
    th[170]="The man who views the world at fifty the same as he did at twenty has wasted thirty years of his life.\n - Muhammad Ali";
    th[171]="What you discover on your own is always more exciting than what someone else discovers for you - it\'s like the marriage between romantic love and an arranged marriage.\n - Terrence Rafferty";
    th[172]="It\'s a helluva start, being able to recognize what makes you happy.\n - Lucille Ball";
    th[173]="Your vision will become clear only when you can look into your own heart. Who looks outside, dreams; who looks inside, awakes.\n - Carl Jung";
    th[174]="Resolve to be thyself; and know that he who finds himself, loses his misery.\n - Matthew Arnold";
    th[175]="Man is least himself when he talks in his own person. Give him a mask, and he will tell you the truth.\n - Oscar Wilde";
    th[176]="The only journey is the journey within.\n - Rainer Maria Rilke";
    th[177]="All men should strive to learn before they die what they are running from, and to, and why.\n - James Thurber";
    th[178]="Man can starve from a lack of self-realization as much as from a lack of bread.\n - Richard Wright";
    th[179]="A man\'s growth is seen in the successive choirs of his friends.\n - Ralph Waldo Emerson";
    th[180]="Knowing yourself is the beginning of all wisdom.\n - Aristotle";
    th[181]="Like an old gold-panning prospector, you must resign yourself to digging up a lot of sand from which you will later patiently wash out a few minute particles of gold ore.\n - Dorothy Bryant";
    th[182]=th[24];
    th[183]="Take wrong turns. Talk to strangers. Open unmarked doors. And if you see a group of people in a field, go find out what they are doing. Do things without always knowing how they\'ll turn out.\n - Randall Munroe";
    th[184]="Life can only be understood backwards; but it must be lived forwards.\n - Soren Kierkegaard";
    th[185]="Life is beautiful, as long as it consumes you. When it is rushing through you, destroying you, life is gorgeous, glorious. It\'s when you burn a slow fire and save fuel, that life\'s not worth having.\n - D. H. Lawrence";
    th[186]="So keep your head high, keep your chin up, and most importantly, keep smiling, because life\'s a beautiful thing and there\'s so much to smile about.\n - Marilyn Monroe";
    th[187]="If you do what you need, you\'re surviving. If you do what you want, you\'re living.";
    th[188]="One day, you\'re 17 and you\'re planning for someday. And then quietly, without you ever really noticing, someday is today. And then someday is yesterday. And this is your life.\n - John Green";
    th[189]="Go confidently in the direction of your dreams. Live the life you have imagined.\n - Henry David Thoreau";
    th[190]="Be always at war with your vices, at peace with your neighbors, and let each new year find you a better man.\n - Benjamin Franklin";
    th[191]="If you are depressed you are living in the past. If you are anxious you are living in the future. If you are at peace you are living in the present.\n - Lao Tzu";
    th[192]="Life is not a problem to be solved, but a reality to be experienced.\n - Soren Kierkegaard";
    th[193]="Life is not always a matter of holding good cards, but sometimes, playing a poor hand well.\n - Jack London";
    th[194]="The best time to plant a tree is twenty-five years ago. The second best time is today.";
    th[195]="Only put off until tomorrow what you are willing to die having left undone.\n - Pablo Picasso";
    th[196]="Let us endeavor so to live that when we come to die even the undertaker will be sorry.\n - Mark Twain";
    th[197]="If you want to live a happy life, tie it to a goal, not to people or things.\n - Albert Einstein";
    th[198]="Hatred paralyzes life; love releases it.\nHatred confuses life; love harmonizes it.\n Hatred darkens life; love illumines it.\n - Martin Luther King Jr";
    th[199]="Life is short, break the rules. Forgive quickly, kiss slowly. Love truly. Laugh uncontrollably and never regret anything that makes you smile.\n - Mark Twain";
    th[200]="You only live once, but if you do it right, once is enough.\n - Mae West";
    th[201]="It is better to be hated for what you are than to be loved for what you are not.\n - Andre Gide";
    th[202]="Life isn't about finding yourself. Life is about creating yourself.\n - George Bernard Shaw";
    th[203]="You cannot change what you refuse to confront.";
    th[204]="Sometimes good things fall apart so better things can fall together.";
    th[205]="Don\'t think of cost.  Think of value.";
    th[206]="Sometimes you need to distance yourself to see things clearly.";
    th[207]="Too many people buy things they don\'t need with money they don\'t have to impress people they don\'t know.  Read Rich Dad, Poor Dad.";
    th[208]="No matter how many mistakes you make or how slow you progress, you are still way ahead of everyone who isn\'t trying.";
    th[209]="If a person wants to be a part of your life, they will make an obvious effort to do so.  Think twice before reserving a space in your heart for people who do not make an effort to stay.";
    th[210]="Making one person smile can change the world - maybe not the whole world, but their world.";
    th[211]="Saying someone is ugly doesn\'t make you any prettier.";
    th[212]="The only normal people you know are the ones you don\'t know very well.";
    th[213]="Life is 10% of what happens to you and 90% of how you react to it.";
    th[214]="The most painful thing is losing yourself in the process of loving someone too much, and forgetting that you are special too.";
    th[215]="It\'s better to be alone than to be in bad company.";
    th[216]="As we grow up, we realize it becomes less important to have more friends and more important to have real ones.";
    th[217]="Making a hundred friends is not a miracle.  The miracle is to make a single friend who will stand by your side even when hundreds are against you.";
    th[218]="Giving up doesn\'t always mean you\'re weak, sometimes it means you are strong enough and smart enough to let go and move on.";
    th[219]="If you really want to do something, you\'ll find a way. If you don\'t, you\'ll find an excuse.";
    th[220]="Don\'t choose the one who is beautiful to the world; choose the one who makes your world beautiful.";
    th[221]="Falling in love is not a choice.  To stay in love is.";
    th[222]="True love isn\'t about being inseparable; it\'s about two people being true to each other even when they are separated.";
    th[223]="While you\'re busy looking for the perfect person, you\'ll probably miss the imperfect person who could make you perfectly happy.";
    th[224]="Never do something permanently foolish just because you are temporarily upset.";
    th[225]="You can learn great things from your mistakes when you aren\'t busy denying them.";
    th[226]="In life, if you don\'t risk anything, you risk everything.";
    th[227]="When you stop chasing the wrong things you give the right things a chance to catch you.";
    th[228]="Every single thing that has ever happened in your life is preparing you for a moment that is yet to come.";
    th[229]="There isn\'t anything noble about being superior to another person.  True nobility is in being superior to the person you once were.";
    th[230]="Trying to be someone else is a waste of the person you are.";
    th[231]="You will never become who you want to be if you keep blaming everyone else for who you are now.";
    th[232]="People are more what they hide than what they show.";
    th[233]="Sometimes people don\'t notice the things others do for them until they stop doing them.";
    th[234]="Don\'t listen to what people say, watch what they do.";
    th[235]="Being alone does not mean you are lonely, and being lonely does not mean you are alone.";
    th[236]="When you have to start compromising yourself and your morals for the people around you, it\'s probably time to change the people around you.";
    th[237]="The smallest act of kindness is worth more than the grandest intention.";
    th[238]="You don\'t drown by falling in the water.  You drown by staying there.";
    th[239]="It\'s better to know and be disappointed than to never know and always wonder.";
    th[240]="Happiness is not determined by what\'s happening around you, but rather what\'s happening inside you.  Most people depend on others to gain happiness, but the truth is, it always comes from within.";
    th[241]="If you tell the truth, it becomes a part of your past.  If you lie, it becomes a part of your future.";
    th[242]="You can\'t start the next chapter of your life if you keep re-reading your last one.";
    th[243]="Things turn out best for people who make the best out of the way things turn out.";
    th[244]="If you don\'t like something, change it.  If you can\'t change it, change the way you think about it.";
}
	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quotes);
		TextView quote_tv = (TextView) findViewById(R.id.tvQuote);
		
		Button nextButton = (Button) findViewById(R.id.buttonNext);
		nextButton.setOnClickListener(this);

		
		int index = (int)(Math.random()*244);
		quote_tv.setText(th[index]);
	    
	}
	public void onClick (View v)
	{
		try
		{
			TextView quote_tv = (TextView) findViewById(R.id.tvQuote);
			int index = (int)(Math.random()*133);
			quote_tv.setText(th[index]);
		}
		catch (Exception e)
		{
			
		}
	}

}
