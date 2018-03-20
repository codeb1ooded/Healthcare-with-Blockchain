from django.conf.urls import url
from django.contrib import admin

from . import views
from . import feeds

urlpatterns = [
    url(r'^$',views.log, name='log'),
    url(r'^authentication/$',views.login_next, name='login_next'),
    url(r'^log_end/$',views.log_end,name='logout'),
    url(r'^signup/$',views.signup,name='signup'),
    url(r'^home/$',views.apiAddBlock,name='home'),
    url(r'^dashboard/$',views.dashboard,name='dashboard'),
    url(r'^add_block/$',views.add_block,name='add_block'),
    url(r'^view_chain/$',views.view_chain,name='view_chain'),
    ]

feeds.prediction_model()

from django.contrib.staticfiles.urls import staticfiles_urlpatterns
urlpatterns += staticfiles_urlpatterns()

from django.conf import settings
from django.conf.urls.static import static
if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
