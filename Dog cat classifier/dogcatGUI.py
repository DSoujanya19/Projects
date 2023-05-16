from keras.models import load_model
import tkinter as tk
from tkinter import *
from tkinter import filedialog
import numpy as np
import cv2
from PIL import ImageTk,Image
# importing model
model=load_model('dog_cat_classifier.h5')

labels={0:'Dog',1:'Cat'}

def upload_image():
    file_path=filedialog.askopenfilename()
    uploaded=Image.open(file_path)
    uploaded=uploaded.resize((250,250))
    im=ImageTk.PhotoImage(uploaded)
    set_img.configure(image=im)
    set_img.image=im
    op.configure(text='')
    classifyimg(file_path)

def classifyimg(file_path):
    img=cv2.imread(file_path,cv2.IMREAD_GRAYSCALE)
    img=cv2.resize(img,(50,50))
    npimg=np.array(img)
    npimg.astype('float32')
    npimg=npimg/255
    npimg=npimg[:].reshape(1,50,50,1)
    preds=model.predict(npimg)
    if preds>=0.5:
        preds=1
    else:
        preds=0
    op.configure(text=labels[preds])

# Main window
top=tk.Tk()
top.geometry("800x800")
top.title('Dog Cat Classifier')
top.configure(background='white')

# Main title
title=tk.Label(top,text='DOG CAT Classifier',pady=20,padx=30,font=('arial',20,'bold'), relief=RAISED )
title.configure(background='white',foreground='#000037')
title.pack(pady=30)

# Upload button 
upload=tk.Button(top,text='Upload an image',command=upload_image,padx=20,pady=20,font=('arial',12,'bold'))
upload.configure(background='#000037',foreground='white')
upload.pack(side=BOTTOM,pady=60)

# image is loaded on this label
set_img=tk.Label(top)
set_img.pack(side=BOTTOM,expand=True)

# output- prediction is printed here
op=tk.Label(top,background='white',foreground='#346969',font=('Arial',28,'bold'))
op.pack(side=BOTTOM,expand=True)

top.mainloop()