#include <iostream>
#include "Node.h"
#include <string>

using std::string;
using std::to_string;

class LinkedList {

	public:
		LinkedList() {
			this->size = 0;
			this->head = 0;
			this->tail = 0;
		}

		void addLast(int value) {
			Node *newPtr = new Node(value);
			if (isEmpty()) {
				this->head = newPtr;
				this->tail = newPtr;
			} else {
				tail->nextPtr = newPtr;
				newPtr->prevPtr = this->tail;
				this->tail = newPtr;
			}
			this->size++;
		}

		void addFirst(int value) {
			Node *newPtr = new Node(value);
			if (isEmpty()) {
				this->head = newPtr;
				this->tail = newPtr;
			} else {
				head->prevPtr = newPtr;
				newPtr->nextPtr = this->head;
				this->head = newPtr;
			}
			this->size++;
		}

		void add(int index, int value) {
			if (index < 0 || index > this->size) {
				throw std::runtime_error("Índice inválido");
			}
			if (index == 0) {
				addFirst(value);
			} else if (index == size) {
				addLast(value);
			} else {
				Node *auxPtr = this->head;
				add(auxPtr, value, index);
			}
		}

		bool isEmpty() {
			return this->size == 0;
		}
		
		int getFirst() {
			if (isEmpty()) {
				throw std::runtime_error("Lista vazia");
			}
			return this->head->value;
		}

		int getLast() {
			if (isEmpty()) {
				throw std::runtime_error("Lista vazia");
			}
			return this->tail->value;
		}
		
		int get(int index) {
			if (index < 0 || index >= this->size) {
				throw std::runtime_error("Índice inválido");
			} else if (index == 0) {
				return getFirst();
			} else if (index == this->size - 1) {
				return getLast();
			} else {
				return get(this->head, index);
			}
		}	

		int removeFirst() {
			if (isEmpty()) {
				throw std::runtime_error("Índice inválido");
			}
			
			int elem = this->head->value;
			if (this->size == 1) {
				delete this->head;
				this->head = NULL;
				this->tail = NULL;
			} else {
				this->head = this->head->nextPtr;
				delete this->head->prevPtr;
				this->head->prevPtr = NULL;
				
			}
			
			this->size--;
			return elem;
		}

		int removeLast() {
			if (isEmpty()) {
				throw std::runtime_error("Índice inválido");
			}

			int elem = this->tail->value;
			if (this->size == 1) {
				delete this->tail;
				this->head = NULL;
				this->tail = NULL;
			} else {
				this->tail = this->tail->prevPtr;
				delete this->tail->nextPtr;
				this->tail->nextPtr = NULL;
			}

			this->size--;
			return elem;
		}

		int remove(int index) {
			if (index < 0 || index >= this->size) {
				throw std::runtime_error("Índice inválido");
			} else if (index == 0) {
				return removeFirst();
			} else if (index == this->size - 1) {
				return removeLast();
			} else {
				return remove(this->head, index);
			}
		}

		bool removeByValue(int value) {
			return removeByValue(this->head, value);	
		}		

		string toString() {
                        string msg = "";
                        if (isEmpty()) {
                                return msg;
                        }

                        Node *auxPtr = this->head;
                        while (auxPtr != 0 && auxPtr != NULL) {
                                msg += to_string(auxPtr->value) + " ";
                                auxPtr = auxPtr->nextPtr;
                        }
                        return msg;
                }
		
		int indexOf(int value) {
			return indexOf(this->head, value, 0);
		}
		
		bool contains(int value) {
			return indexOf(value) != -1;
		}

		int lastIndexOf(int value) {
			return lastIndexOf(this->tail, value, size-1);
		}
		
		int getSize() {
			return this->size;
		}

	private:
		int size;
		Node *head;
		Node *tail;

		void add(Node *currentPtr, int value, int index) {
                        if (index == 0) {
                                Node *newPtr = new Node(value);
                                currentPtr->prevPtr->nextPtr = newPtr;
                                newPtr->prevPtr = currentPtr->prevPtr;
                                newPtr->nextPtr = currentPtr;
                                currentPtr->prevPtr = newPtr;
                                this->size++;
                        } else {
                                add(currentPtr->nextPtr, value, index-1);
                        }
                }

		int get(Node *currentPtr, int index) {
			if (index == 0) {
				return currentPtr->value;
			} else {
				return get(currentPtr->nextPtr, index-1);
			}
		}

		int remove(Node *currentPtr, int index) {
			if (index == 0) {
				currentPtr->prevPtr->nextPtr = currentPtr->nextPtr;
				currentPtr->nextPtr->prevPtr = currentPtr->prevPtr;
				int elem = currentPtr->value;
				delete currentPtr;
				this->size--;
				return elem;
			} else {
				return remove(currentPtr->nextPtr, index-1);
			}
		}
		
		bool removeByValue(Node *currentPtr, int value) {
			if (currentPtr == NULL || currentPtr == 0) {
				return false;
			} else if (currentPtr->value == value) {
				if (this->head == currentPtr) {
					removeFirst();
				} else if (this->tail == currentPtr) {
					removeLast();
				} else {
					remove(currentPtr, 0);
					
				}
				return true;
			} else {
				return removeByValue(currentPtr->nextPtr, value);
			}
		}

		int indexOf(Node *currentPtr, int value, int currentIndex) {
			if (currentPtr == NULL || currentPtr == 0) {
				return -1;
			} else if (currentPtr->value == value) {
				return currentIndex;
			} else {
				return indexOf(currentPtr->nextPtr, value, currentIndex+1);
			}
		}

		int lastIndexOf(Node *currentPtr, int value, int currentIndex) {
			if (currentPtr == NULL || currentPtr == 0) {
				return -1;
			} else if (currentPtr->value == value) {
				return currentIndex;
			} else {
				return lastIndexOf(currentPtr->prevPtr, value, currentIndex-1);
			}
		}
};

