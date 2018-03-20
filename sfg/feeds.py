from .models import *
import datetime as date
import csv,numpy,pandas
from sklearn.multiclass import OneVsOneClassifier
from sklearn.svm import LinearSVC
from sklearn import svm
from django.contrib.staticfiles.templatetags.staticfiles import static

class Blockchain:
  def create_genesis_block(self):
    if not Block.objects.all().exists():
      b=Block()
      b.index=0
      b.timestamp=date.datetime.now()
      b.title="Genesis Block"
      b.image="/media/"
      b.text="random text"
      b.previous_hash=-1
      sha = hasher.sha256()
      a= str(b.index)+str(b.timestamp)+str(b.title)+str(b.text)+str(b.image)+str(b.previous_hash)
      sha.update(a.encode())
      b.hash=sha.hexdigest()
      b.save()

  def __init__(self):
    self.create_genesis_block()

  def add_block(self,data):
    last_block=Block.objects.latest('timestamp')
    b=Block()
    b.user=User.objects.get(username=data['user'])
    b.index=last_block.index+1
    b.timestamp=date.datetime.now()
    b.title=data['title']
    b.image=data['image']
    b.text=data['text']
    b.previous_hash=last_block.hash
    sha = hasher.sha256()
    a= str(b.user)+str(b.index)+str(b.timestamp)+str(b.title)+str(b.text)+str(b.image)+str(b.previous_hash)
    sha.update(a.encode())
    b.hash=sha.hexdigest()
    b.save()
    return b

blockchain= Blockchain()

clf=svm.SVC()
def prediction_model():
    print("fitting model")
    filename='../RajHack/static/sfg/csv/trainLabels.csv'
    names=['image','level']
    data = pandas.read_csv(filename, names=names)
    im=data.image
    image=im.values.reshape(-1, 1)
    X, y = image, data.level
    clf.fit(X, y)
    print("model is now fit")

def predict(val):
    pred=clf.predict(numpy.asarray(val))
    return(pred)

import json,requests

def ocr_file(filename, overlay=False, api_key='61a8cd0dbb88957', language='eng'):
    """ OCR.space API request with local file.
      Python3.5 - not tested on 2.7
    :param filename: Your file path & name.
    :param overlay: Is OCR.space overlay required in your response.
                  Defaults to False.
    :param api_key: OCR.space API key.
                  Defaults to 'helloworld'.
    :param language: Language code to be used in OCR.
                  List of available language codes can be found on https://ocr.space/OCRAPI
                  Defaults to 'en'.
    :return: Result in JSON format.
    """
    payload = {'isOverlayRequired': overlay,
        'apikey': api_key,
        'language': language,
    }
    filename=filename.replace('%20',' ')
    with open(filename, 'rb') as f:
        r = requests.post('https://api.ocr.space/parse/image',files={filename: f},data=payload,)

    result=r.content.decode()
    try: #for python 2.7
        result.decode("utf-8")
    except:
        pass

    result=json.loads(result)
    return result['ParsedResults'][0]['ParsedText']
