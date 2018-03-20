from django import forms
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User

class SignUpForm(UserCreationForm):
    first_name = forms.CharField(max_length=30, required=False, widget=forms.TextInput(attrs={'class': 'signupinput firstname','placeholder':'First Name*'}))
    last_name = forms.CharField(max_length=30, required=False, widget=forms.TextInput(attrs={'class': 'signupinput lastname','placeholder':'Last Name'}))
    email = forms.EmailField(max_length=254, widget=forms.EmailInput(attrs={'class': 'signupinput email','placeholder':'Email ID*'}))
    password1 = forms.CharField(widget=forms.PasswordInput(attrs={'class': 'signupinput password1','placeholder':'Password*'}))
    password2 = forms.CharField(widget=forms.PasswordInput(attrs={'class': 'signupinput password2','placeholder':'Confirm Password*'}))

    class Meta:
        model = User
        fields = ['username', 'first_name', 'last_name', 'email', 'password1', 'password2']
        
        widgets = {
        'username': forms.TextInput(attrs={'class': 'signupinput username','placeholder':'Username*'}),      
        }

