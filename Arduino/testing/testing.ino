int led12 = 12;
int led13 = 13;

void setup() {
  // put your setup code here, to run once:
  for (int i = 11; i < 13; i++) {
    pinMode(i, OUTPUT); 
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  //This is a comment test on Ipad, the arrow keys dont work :(
    digitalWrite(led13, HIGH);
    digitalWrite(led12, LOW);
    delay(100);
    digitalWrite(led13, LOW);
    digitalWrite(led12, HIGH);
    delay(100);
}
