package com.singgalangjaya.assets;

public class Matrix<T> {
    private T[][] matrix;
    private int row;
    private int col;

    @SuppressWarnings("unchecked")
    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.matrix = (T[][]) new Object[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.matrix[i][j] = null;
            }
        }
    }

    public Matrix(Matrix<T> matriks) {
        this.row = matriks.getRow();
        this.col = matriks.getCol();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.matrix[i][j] = matriks.getMatrix(i, j);
            }
        }
    }

    public void setMatrix(int row, int col, T value) {
        this.matrix[row][col] = value;
    }

    public T getMatrix(int row, int col) {
        return this.matrix[row][col];
    }
    
    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean isEmpty () {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (this.matrix[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFull () {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (this.matrix[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}

