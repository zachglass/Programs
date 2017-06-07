#include <stdio.h>

//This should also be a comment in this language too....
/*
This should also work
And I shall be learning to do some more C than I already know
*/

//This is a comment from IPad, arrow keys still dont work... maybe its just SSH

void test() {
    printf("World!\n");
}

void testing() {
    printf("Hello\n");
}

main() {
    for (int i = 0; i < 10; i++) {
        testing();
        test();
    }
}
