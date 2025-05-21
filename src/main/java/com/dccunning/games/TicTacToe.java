package com.dccunning.games;

import java.util.*;

public class TicTacToe {

    private static final char EMPTY = ' ';
    private static final int SIZE = 3;

    private final char[][] board = new char[SIZE][SIZE];
    private char currentPlayer = 'X';

    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
//        new TicTacToe().play();

        // Binary search tree
        TreeMap<Integer, String> binarySearchTree = new TreeMap<>();
        binarySearchTree.put(1, "A");
        binarySearchTree.remove(1);
        binarySearchTree.get(1);
        binarySearchTree.floorKey(2);

        // Binary heap
        PriorityQueue<Integer> heaps = new PriorityQueue<>(Collections.reverseOrder());
        heaps.add(1);
        heaps.add(9);
        heaps.add(3);
        System.out.println(heaps.poll());
        System.out.println(heaps.peek());

        //Stack
        Stack<Character> stack = new Stack<>();
        stack.push('[');
        stack.push(']');
        stack.pop();
        System.out.println(stack.peek());

    }

    private void play() {
        initBoard();

        while (true) {
            printBoard();
            int move = promptMove();
            placeMark(move);

            if (hasWinner()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isDraw()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }
            switchPlayer();
        }

    }

    /**
     * Set board to an empty 3x3 grid.
     */
    private void initBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = EMPTY;
            }
        }
    }

    /**
     * Display the current state of the board.
     */
    private void printBoard() {
        System.out.println();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                char mark = board[r][c];
                System.out.print(mark);
                if (c < SIZE - 1) {System.out.print(" | ");}
            }
            if (r < SIZE - 1) {
                System.out.println("\n--+---+--");
            }
        }
        System.out.println();
    }

    /**
     * Ask the user which move they would like to make, and return the selected position.
     * @return The integer position of the successful move.
     */
    private int promptMove() {
        while (true) {
            System.out.print("Player " + currentPlayer +", enter position (1-9): ");
            String input = in.nextLine().trim();
            try {
                int pos = Integer.parseInt(input);
                if (pos < 1 || pos > SIZE * SIZE) throw new NumberFormatException();

                int r = (pos - 1) / SIZE, c = (pos - 1) % SIZE;
                if (board[r][c] != EMPTY) {
                    System.out.println("Square already taken. Try again.");
                } else {
                    return pos;
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Enter number from 1 to " + (SIZE * SIZE));
            }
        }
    }

    /**
     * Places a mark of the current user on the board for their move.
     * @param pos the integer position on the board to move on.
     */
    private void placeMark(int pos) {
        int r = (pos - 1) / SIZE;
        int c = (pos - 1) % SIZE;
        board[r][c] = currentPlayer;
    }

    /**
     * Switch the player from X if it was O and to O if it was X.
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /**
     * Check for a wining line in tic-tac-toe.
     * @param a Value 1
     * @param b Value 2
     * @param c Value 3
     * @return True if all chars are the same and non-empty, false otherwise
     */
    private boolean line(char a, char b, char c) {
        return a != EMPTY && a == b && a == c;
    }

    /**
     * Check if there are any wining lines on the board.
     * @return True if there are, false otherwise
     */
    private boolean hasWinner() {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            // Rows
            if (line(board[i][0], board[i][1], board[i][2])) return true;
            // Columns
            if (line(board[0][i], board[1][i], board[2][i])) return true;
        }
        // Check diagonals
        return (line(board[0][0], board[1][1], board[2][2]) ||
                line(board[0][2], board[1][1], board[2][0]));
    }

    /**
     * Determine if the game is a draw by the fact that no more moves can be made.
     * @return True if all cells filled up.
     */
    private boolean isDraw() {
        for (char[] row: board) {
            for (char cell: row) {
                if (cell == EMPTY) return false;
            }
        }
        return true;
    }
}
