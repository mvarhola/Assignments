import cv2
import numpy as np
import math
import random
import time


control_group = bool(random.getrandbits(1)); #randomly assign control group

cap = cv2.VideoCapture(0)


finger_array = [5,2,3,1,4,5,2,3,1,4,1,5,2,3,2,1,2,3,4,5]

if not control_group:
    random.shuffle(finger_array)

start_time = time.time()

while(cap.isOpened()):

    ret, img = cap.read()

    img = cv2.resize(img, (600, 480)) 

    cv2.rectangle(img,(0,300),(0,100),(0,255,0),0)
    crop_img = img[0:300, 0:300]
    grey = cv2.cvtColor(crop_img, cv2.COLOR_BGR2GRAY)
    value = (35, 35)
    blurred = cv2.GaussianBlur(grey, value, 0)
    _, thresh1 = cv2.threshold(blurred, 127, 255,
                               cv2.THRESH_BINARY_INV+cv2.THRESH_OTSU)
    cv2.imshow('Thresholded', thresh1)

    (version, _, _) = cv2.__version__.split('.')

    if version is '3':
        image, contours, hierarchy = cv2.findContours(thresh1.copy(), \
               cv2.RETR_TREE, cv2.CHAIN_APPROX_NONE)
    elif version is '2':
        contours, hierarchy = cv2.findContours(thresh1.copy(),cv2.RETR_TREE, \
               cv2.CHAIN_APPROX_NONE)

    cnt = max(contours, key = lambda x: cv2.contourArea(x))
    
    x,y,w,h = cv2.boundingRect(cnt)
    cv2.rectangle(crop_img,(x,y),(x+w,y+h),(0,0,255),0)
    hull = cv2.convexHull(cnt)
    drawing = np.zeros(crop_img.shape,np.uint8)
    cv2.drawContours(drawing,[cnt],0,(0,255,0),0)
    cv2.drawContours(drawing,[hull],0,(0,0,255),0)
    hull = cv2.convexHull(cnt,returnPoints = False)
    defects = cv2.convexityDefects(cnt,hull)
    count_defects = 0
    cv2.drawContours(thresh1, contours, -1, (0,255,0), 3)

    for i in range(defects.shape[0]):
        s,e,f,d = defects[i,0]
        start = tuple(cnt[s][0])
        end = tuple(cnt[e][0])
        far = tuple(cnt[f][0])
        a = math.sqrt((end[0] - start[0])**2 + (end[1] - start[1])**2)
        b = math.sqrt((far[0] - start[0])**2 + (far[1] - start[1])**2)
        c = math.sqrt((end[0] - far[0])**2 + (end[1] - far[1])**2)
        angle = math.acos((b**2 + c**2 - a**2)/(2*b*c)) * 57
        if angle <= 90:
            count_defects += 1
            cv2.circle(crop_img,far,1,[0,0,255],-1)
        #dist = cv2.pointPolygonTest(cnt,far,True)
        cv2.line(crop_img,start,end,[0,255,0],2)
        #cv2.circle(crop_img,far,5,[0,0,255],-1)

        print finger_array
    if count_defects+1 == finger_array[0]:
        cv2.putText(img,"Correct!", (50,50), cv2.FONT_HERSHEY_SIMPLEX, 2,(255,255,255))
        finger_array.pop(0)
    else:
        print(count_defects)
        cv2.putText(img,"Show "+str(finger_array[0])+" fingers", (50,50),\
                    cv2.FONT_HERSHEY_SIMPLEX, 2,(255,255,255))
 

    #cv2.imshow('drawing', drawing)
    #cv2.imshow('end', crop_img)
    cv2.imshow('Gesture', img)
    all_img = np.hstack((drawing, crop_img))
    cv2.imshow('Contours', all_img)
    k = cv2.waitKey(10)

    if not finger_array:
        end_time = time.time()
        final_time = end_time - start_time

        print("FINISHED!!!")
        
        output_filename = "control_group.txt" if control_group else "test_group.txt"

        f = open('./output/'+output_filename, 'a+')
        f.write(str(control_group)+","+str(final_time))
        f.close()

        break

    if k == 27:
        break
