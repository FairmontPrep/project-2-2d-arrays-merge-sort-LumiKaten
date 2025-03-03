import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {
    public int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE]; 
    private String[][] piecesArray;

    public GameBoard() {
        setTitle("Poke Board");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE)); 

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel();
                squares[row][col].setBackground((row + col) % 2 == 0 ? new Color(255, 251, 240) : Color.PINK);
                add(squares[row][col]);
            }
        }

        piecesArray = new String[32][3];  
        loadPieces();
        
        mergeSort(piecesArray, 0, piecesArray.length - 1); // Sort using Merge Sort

        populateBoard(); // Update board after sorting
    }

    private void mergeSort(String[][] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(String[][] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        String[][] leftArr = new String[n1][3];
        String[][] rightArr = new String[n2][3];

        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (Integer.parseInt(leftArr[i][2]) <= Integer.parseInt(rightArr[j][2])) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }

    private void populateBoard() {
        int pieceRow = 0;
        int squareName = 0;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (pieceRow < piecesArray.length) {
                    int pokePosition = Integer.parseInt(piecesArray[pieceRow][2]);
                    if (squareName == pokePosition) {
                        String imagePath = piecesArray[pieceRow][0];
                        String hpText = piecesArray[pieceRow][1];

                        ImageIcon icon = new ImageIcon(imagePath);
                        Image scaledImage = icon.getImage().getScaledInstance(40, 42, Image.SCALE_SMOOTH);

                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                        JLabel textLabel = new JLabel(hpText, SwingConstants.CENTER);
                        textLabel.setForeground(Color.BLACK);

                        JPanel piecePanel = new JPanel(new BorderLayout());
                        piecePanel.setOpaque(false);
                        piecePanel.add(pieceLabel, BorderLayout.CENTER);
                        piecePanel.add(textLabel, BorderLayout.SOUTH);

                        squares[row][col].setLayout(new BorderLayout());
                        squares[row][col].add(piecePanel, BorderLayout.CENTER);

                        pieceRow++;
                    }
                }
                squareName++;
            }
        }

        revalidate();
        repaint();
    }

    private void loadPieces() {
        piecesArray[0] = new String[]{"lays.png", "HP:200", "9"};
        piecesArray[1] = new String[]{"lays.png", "HP:200", "10"};
        piecesArray[2] = new String[]{"lays.png", "HP:200", "11"};
        piecesArray[3] = new String[]{"lays.png", "HP:200", "12"};
        piecesArray[4] = new String[]{"lays.png", "HP:200", "13"};
        piecesArray[5] = new String[]{"lays.png", "HP:200", "14"};
        piecesArray[6] = new String[]{"pringles.png", "HP:172", "1"};
        piecesArray[7] = new String[]{"fritos.png", "HP:100", "15"};
        piecesArray[8] = new String[]{"cheetos.png", "HP:104", "16"};
        piecesArray[9] = new String[]{"cheetos.png", "HP:123", "17"};
        piecesArray[10] = new String[]{"cheetos.png", "HP:134", "18"};
        piecesArray[11] = new String[]{"ruffles.png", "HP:155", "19"};
        piecesArray[12] = new String[]{"sunchips.png", "HP:182", "20"};
        piecesArray[13] = new String[]{"coke.png", "HP:111", "58"};
        piecesArray[14] = new String[]{"sprite.png", "HP:300", "49"};
        piecesArray[15] = new String[]{"jarritos.png", "HP:1", "50"};
        piecesArray[16] = new String[]{"fanta.png", "HP:2", "51"};
        piecesArray[17] = new String[]{"7up.png", "HP:3", "52"};
        piecesArray[18] = new String[]{"m.png", "HP:4", "53"};
        piecesArray[19] = new String[]{"canada-dry.png", "HP:5", "56"};
        piecesArray[20] = new String[]{"pepsi.png", "HP:6", "57"};
        piecesArray[21] = new String[]{"m.png", "HP:4", "54"};
        piecesArray[22] = new String[]{"sunchips.png", "HP:182", "50"};
        piecesArray[23] = new String[]{"m.png", "HP:4", "55"};
        piecesArray[24] = new String[]{"pringles.png", "HP:172", "2"};
        piecesArray[25] = new String[]{"pringles.png", "HP:172", "3"};
        piecesArray[26] = new String[]{"pringles.png", "HP:172", "4"};
        piecesArray[27] = new String[]{"pringles.png", "HP:172", "5"};
        piecesArray[28] = new String[]{"pringles.png", "HP:172", "6"};
        piecesArray[29] = new String[]{"pringles.png", "HP:172", "7"};
        piecesArray[30] = new String[]{"coke.png", "HP:111", "58"};
        piecesArray[31] = new String[]{"coke.png", "HP:111", "59"};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}
