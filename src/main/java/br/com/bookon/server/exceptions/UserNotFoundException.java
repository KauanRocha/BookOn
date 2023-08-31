package br.com.bookon.server.exceptions;

class UserNotFoundException extends RuntimeException {
	UserNotFoundException(Integer id) {
	    super("Could not find User " + id);
	  }
}