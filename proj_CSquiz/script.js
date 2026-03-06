const winPatterns = [
  [0, 1, 2],
  [0, 3, 6],
  [0, 4, 8],
  [1, 4, 7],
  [2, 5, 8],
  [2, 4, 6],
  [3, 4, 5],
  [6, 7, 8],
];
let cells = [...document.querySelectorAll('.cell')];
console.log(cells);
const player = ['X', 'O'];
let currentPlayer = player[0];
const playerIcon = document.getElementById('playerIcon');

if (currentPlayer == player[0]) {
  playerIcon.src = './img/player0.png';
} else {
  playerIcon.src = './img/player1.png';
}

cells.forEach((cell) => {
  cell.addEventListener('click', () => {
    const index = cell.dataset.index;
    window.location.href = `./quizes/quiz${Number(index) + 1}.html`;
  });
});
