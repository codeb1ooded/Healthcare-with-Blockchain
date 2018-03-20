from __future__ import unicode_literals
from django.db import models
from django.contrib.auth.models import User
from django.core.files.base import ContentFile
import hashlib as hasher


class Patient(models.Model):
    patient_id = models.AutoField(null=False,primary_key=True)
    username = models.CharField(max_length=500)
    name = models.CharField(max_length=500)

    def __unicode__(self):
        return unicode(self.name)


class Block(models.Model):
    user = models.ForeignKey(User)
    index = models.IntegerField()
    timestamp = models.DateTimeField(auto_now_add=True)
    title  = models.CharField(max_length=250)
    image = models.CharField(max_length=5000)
    text = models.CharField(max_length=50000)
    previous_hash = models.CharField(max_length=256)
    hash = models.CharField(max_length=256)

    def __unicode__(self):
        return unicode(self.title)


class Patient(models.Model):
    patient_id = models.AutoField(null=False,primary_key=True)
    username = models.CharField(max_length=500)
    name = models.CharField(max_length=500)

    def __unicode__(self):
        return unicode(self.name)

class PatientAndBlock(models.Model):
    PATIENT_BLOCK = models.ForeignKey(
        Block,
        on_delete=models.CASCADE,
		null=True,
		blank=True,
    )
    PATIENT = models.ForeignKey(
        Patient,
        on_delete=models.CASCADE,
		null=True,
		blank=True,
    )
    def __unicode__(self):
        return unicode(self.PATIENT)
