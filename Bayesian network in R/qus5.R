library(bnlearn)
library(caret)
set.seed(100)
tIndex <- createDataPartition(course_score$QP, p=0.7)$Resample1
train <- course_score[tIndex, ]
test <- course_score[-tIndex, ]
trainda <- bn.fit(hc(train, score="k2"), train)
testda <- bn.fit(hc(test,score="k2"),test)
predicted_1 <- predict(trainda,node="QP",data=train)
predicted_2 <- predict(testda,node="QP",data=test)
tabletrain <- table(train$QP, predicted_1)
testtable <- table(test$QP, predicted_2)
trainAcc = (tabletrain[1,1]+tabletrain[2,2])/sum(tabletrain)
testAcc = (testtable[1,1]+testtable[2,2])/sum(testtable)
message("Accuracy")
print(round(cbind("Training Accuracy" =trainAcc, "Test Accuracy" =testAcc), 4))