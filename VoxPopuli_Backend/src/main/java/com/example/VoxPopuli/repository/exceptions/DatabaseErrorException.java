package com.example.VoxPopuli.repository.exceptions;

public class DatabaseErrorException extends RuntimeException {
  public DatabaseErrorException(String message) {
        super(message);
    }
  public DatabaseErrorException() {
    super(new Exception("Database error."));
  }
}
