library(bnlearn) # used for bayesian network
score <- c("AA", "AB", "BB", "BC", "CC", "CD", "DD", "F")
course_score<-read.table("2020_bn_nb_data.txt",header=TRUE)
course_score_network<-hc(course_score, score = "k2") #hc function used for hierarichal clustering
print("Dependencies between courses is represeted as :")
plot(course_score_network)