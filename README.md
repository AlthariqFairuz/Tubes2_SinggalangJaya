# SinggalangJaya Harvest Game
> Tugas Besar 2 IF2210 Pemrograman Berorientasi Objek


## General Information
This project involves the creation of a farm management program. The farm consists of multiple plots arranged in a matrix-like fashion, where each plot can be used for planting crops or housing animals. The program resembles a card game where each card represents an object, such as a plant or an animal. These cards can be placed on the farm or sold at a store if they are in the player's active deck. Additionally, objects on the farm can be destroyed in random bear attacks. The program also features save & load functionality to store and retrieve the program state in a text format. Moreover, the program is extensible, providing plugin support, allowing users to easily add file formats for save & load besides the text format.


## Requirements
- Java JDK 22 or above
- Maven


## Features
List the ready features here:
1. The game supports two-player mode
2. A 4x5 grid is available for planting and housing animals
3. The interface displays the current turn and each player's score
4. Players can interact with an active deck by dragging and dropping cards
5. Clicking on a cell reveals detailed information about the animal/plant
6. Animals/plants can be harvested to produce product cards
7. The shop facilitates transactions to earn money
8. Cards are categorized into animals, plants, products, and items


## Setup
1. Intall all requirements
2. Clone this repository using
```
https://github.com/AlthariqFairuz/Tubes2_SinggalangJaya.git
```
3. Change directory to the cloned repository
```
cd Tubes2_SinggalangJaya
```
4. Navigate to `src` folder and typing
```
mvn clean package
```
5. Running the program with
```
java -jar gui/target/gui-1.0-SNAPSHOT-jar-with-dependencies.jar
```
5. Enjoy the game with your friend!



## Authors
| Name                             | NIM       |
| -------------------------------- | -------- |
| Ihsan Nor Ramadhan                | 10023465 |
| Dewantoro Triatmojo           | 13522011 |
| Muhammad Althariq Fairuz              | 13522027 |
| Randy Verdian                    | 13522067 |
| Emery Fathan Zwageri | 13522079 |
| Azmi Mahmud Bazeid           | 13522109 |

