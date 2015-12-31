# TexasHoldem-Simulator 
Calcurate Probability of TexasHoldem various situation.
This is based on Montecarlo Simuration.

This program is written by scala.

### Usage
 - calcurate hand rank probability in five or seven poker hand.

 ```
 object Main extends App{
   val deck = new NormalShoe(1)
   val handSimurator = new HandSimurator()
   handSimurator.printFiveCardHandProbability()
   println("")
   handSimurator.printSevenCardHandProbability()
 }

### Result
 when we try 10,000,000 random hand. 

---FIVE CARD POKER HAND PROBABIRITY---
STRAIGHT_FLUSH : 136 : 0.0014%
FOUR_OF_KIND : 2421 : 0.0242%
FULL_HOUSE : 14404 : 0.1440%
FLUSH : 19710 : 0.1971%
STRAIGHT : 36433 : 0.3643%
THREE_OF_KIND : 211217 : 2.1122%
TWO_PAIR : 474728 : 4.7473%
ONE_PAIR : 4224100 : 42.2410%
HIGH_CARD : 5016851 : 50.1685%

---SEVEN CARD POKER HAND PROBABIRITY---
STRAIGHT_FLUSH : 18714 : 0.1871%
FOUR_OF_KIND : 17027 : 0.1703%
FULL_HOUSE : 258974 : 2.5897%
FLUSH : 286931 : 2.8693%
STRAIGHT : 423281 : 4.2328%
THREE_OF_KIND : 482893 : 4.8289%
TWO_PAIR : 2351270 : 23.5127%
ONE_PAIR : 4397598 : 43.9760%
HIGH_CARD : 1763312 : 17.6331%

