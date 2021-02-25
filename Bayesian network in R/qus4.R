library(bnlearn)
library(caret)
set.seed(100)
tIndex <- createDataPartition(course_score$QP, p=0.7)$Resample1
train <- course_score[tIndex, ]
test <- course_score[-tIndex, ]
library(e1071)
nbc <- naiveBayes(QP~EC100+EC160+IT101+IT161+MA101+PH100+PH160+HS101, data = train)
printALL = function(model) {
  trainPred = predict(model, newdata = train, type = "class")
  trainTable = table(train$QP, trainPred)
  testPred = predict(nbc, newdata = test, type = "class")
  testTable = table(test$QP, testPred)
  trainAcc = (trainTable[1,1]+trainTable[2,2])/sum(trainTable)
  testAcc = (testTable[1,1]+testTable[2,2])/sum(testTable)
  message("Accuracy")
  print(round(cbind("Training Accuracy" =trainAcc, "Test Accuracy" =testAcc), 4))
}
printALL(nbc)
