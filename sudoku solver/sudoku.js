let numSelected = null
let boardnumsel = null
let temp = 0
let freeze=false
let solved=false
let inputlist = [[0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0]]

window.onload = function () {
    setup();  
}
//sets up the intial config (Sudoku box and numbers set under it)
function setup() {
    for (let i = 1; i <= 9; i++){
        let tile = document.createElement("div");
        tile.id=1;
        tile.classList.add("usertile");
        tile.textContent=i;
        document.getElementById("digits").appendChild(tile);
        tile.addEventListener("click",selectedNum)
    }
    for (let i = 1; i <= 9; i++){
        for (let j = 1; j <= 9; j++) {
            let tile = document.createElement("div");
            tile.classList.add("usertileb");
            tile.id= ""+i+j;
            tile.addEventListener("click", selectedBoardBox)
            document.getElementById("board").appendChild(tile);
            if (i == 3 || i == 6) {
                tile.classList.add("hor-line");
            }
            if (j == 3 || j == 6) {
                tile.classList.add("ver-line");
            }
        }
    }
}
//Everytime a digit is selected from the set provided under the sudoku box,this function is called. It keeps track of the chosen num
function selectedNum(gloinputlist) {
     if (numSelected != null) {
        numSelected.classList.remove("selected");
    }
    numSelected = this; 
    numSelected.classList.add("selected");
}
//chosen number in digit set is placed in the corresponding selected sudoku box
function selectedBoardBox() {
    if (!freeze) {
        selectedBoardBox = this;
        let s = selectedBoardBox.id.split();
        inputlist[(parseInt(s / 10 % 100)) - 1].splice(s % 10 - 1, 1, numSelected.innerText);
        selectedBoardBox.innerText = numSelected.innerText;
        selectedBoardBox.classList.add("boardSelected");
    }
}
//Calls sudoku solver when the given sudoku question configuration is right
function getList() {
     freeze = true;
     let output=checkConfigSudoku()
    if (output == "Valid") {
        document.getElementById("right").innerText = " Solution is here"
        //solver solves the sudoku
        solver(0, 0);
        //fill sudoku updates the cells of the page viewed by the user
        fillSudoku()
    } else {
        document.getElementById("wrong").innerText = " Given sudoku is invalid\nCheck it. Refresh and re-enter a valid sudoku" 
    }
}
//checks the  configuration of given sudoku question
function checkConfigSudoku() {
    for (let i = 0; i < 9; i++){
        for (let j = 0; j < 9; j++) {
            if (inputlist[i][j] == 0) {
                continue;
            }
            temp = inputlist[i][j];
            inputlist[i][j] = 0;
            if (checkAvail(temp, i, j)==false) {
                return "Invalid";
            }
               inputlist[i][j] = temp;
        }
    }
 return "Valid"
}
//Used by sudoku config to check the configuration of the question
function checkAvail(val, r, c) {
    for (let i = 0; i < 9; i++){
        if (inputlist[r][i] == val) {
            return false; 
        }
        if (inputlist[i][c] == val) {
              return false;
        }
    }
    
    for (let i = 3 * parseInt(r / 3); i <= (3 * parseInt(r / 3) + 2); i++){
        for (let j = 3 * (parseInt(c / 3)); j <= 3 * parseInt(c / 3) + 2; j++){
            if (inputlist[i][j] == val) {
                return false;
            }
        }
    }
    return true;
}
//Resets the sudoku block when reset button is clicked
function reset() {
    window.location.reload()
}
function solver(r, c) {
    if (inputlist[r][c] != 0 && r == 8 && c == 8) {
        return 
    } else if (inputlist[r][c] != 0) {
        if (c != 8) {
            solver(r, (c + 1) % 9)
        } else {
            solver((r + 1) % 9, (c + 1) % 9)
        }
    } else {
        for (let i = 1; i <= 9; i++){
            if (checkAvail(i, r, c, 3 * (parseInt(r / 3)) + parseInt(c / 3)) == true) {
                inputlist[r][c] = i
                if (r == 8 && c == 8) {
                    solved = true
                    return 
                } else {
                    if (c != 8) {
                        solver(r, (c + 1) % 9)
                    } else {
                        solver((r + 1) % 9, (c + 1) % 9)
                    }
                    if (solved == false) {
                        inputlist[r][c]=0
                    } 
                }
            }
        }
    }
}
function fillSudoku() {
    let val = 1;
    for (let i = 1; i <= 9; i++){
        for (let j = 1; j <= 9; j++) {
            val = document.getElementById("" + i + j)
            if (val.innerText=="") {
                val.innerText=inputlist[i-1][j-1]
            } 
        }
    }
}