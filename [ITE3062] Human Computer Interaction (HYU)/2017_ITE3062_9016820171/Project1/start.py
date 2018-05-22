import numpy as np
import cv2
import os, random
import record
import tts

#open random image
filename = random.choice(os.listdir(os.path.join(os.path.dirname(__file__),"./images")))
output_filename = filename+".csv"
#create image object for processing
img = cv2.imread("./images/"+filename)
cv2.putText(img,'describe this image',(10,34), cv2.FONT_HERSHEY_SIMPLEX, 1, (255,255,255), 2, cv2.LINE_AA)
cv2.imshow('image',img)

cv2.waitKey(1)
age = raw_input("Please enter your age: ")
print("Please speak in to the microphone")
record.record_to_file('recording.wav')
print("Done! - result written to recording.wav")
print("Analyzing input with IBM Watson...")
output_line = tts.analyze()


print("Writing results to "+output_filename)
f = open('./output/'+output_filename, 'a+')
f.write(age+","+output_line)
f.close()
cv2.destroyAllWindows()
