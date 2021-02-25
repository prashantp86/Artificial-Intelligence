probability <- 0.0
final_grade = ""
for(value in score) {
  temp <- cpquery(course_grades_bn_fit, event = (PH100== value), evidence = (EC100=="DD" & IT101=="CC" & MA101=="CD"))
  if(temp > probability) {
    final_grade <- value
    probability <- temp
  }
}
print(final_grade)