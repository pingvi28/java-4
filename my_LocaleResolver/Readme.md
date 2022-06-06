не знаю, засчитается ли семестрвока как 3 задние
- [как jpql](https://github.com/pingvi28/java-4/blob/main/semester_work/src/main/java/ru/kpfu/itis/kashapova/repository/UserStatisticRepository.java)
если нет, не страшно =)

## 5 Задание 
## I 
<p align="center"><img src="https://github.com/pingvi28/java-4/blob/main/my_LocaleResolver/img/1.png" width="1000"></p>
<p align="center"><img src="https://github.com/pingvi28/java-4/blob/main/my_LocaleResolver/img/2.png" width="1000"></p>
<p align="center"><img src="https://github.com/pingvi28/java-4/blob/main/my_LocaleResolver/img/3.png" width="1000"><</p>

## II 
<p align="center"><img src="https://github.com/pingvi28/java-4/blob/main/my_LocaleResolver/img/4.png" width="1000"></p>

## О программе 

Вы можете найти MainController. Для первой части задания используется мапинг на (value = "/{locale:en|fr|ru}/login")
Для второй части собственно (value = "/{locale:EN|RU}/login2")
<p align="center"><img src="https://github.com/pingvi28/java-4/blob/main/my_LocaleResolver/img/5.png" width="1000"></p>

### I)  
  Работа первой части зависит от:
 
- [LocaleInterceptorUrl](https://github.com/pingvi28/java-4/blob/main/my_LocaleResolver/src/main/java/ru/kpfu/itis/kashapova/config/LocaleInterceptorUrl.java)
- [LocaleResolverUrl](https://github.com/pingvi28/java-4/blob/main/my_LocaleResolver/src/main/java/ru/kpfu/itis/kashapova/config/LocaleResolverUrl.java).

Метод preHandle() из первого класса перехватывает HttpServletRequest request, HttpServletResponse response - > и после проверки существования localeResolver, пытается на основе URL определить локаль (уже второй класс, который imp LocaleResolver). 
Этот класс из HttpServletRequest request вытаскивает URI и просто сравнивает через if существование из заданного списка
  Тк Locale.Rus  не существует == locale = new Locale("ru", "RU");
  
  - Если необходимая локаль не найдена, то просто присваивается дефолтный Local.ENGLISH
  
  После определения локали в первой классе вызывается setLocale

 ### II) 
  Получаю локаль в mainController (@PathVariable String locale) -> EN/RU -> model.addAttribute("local", localService.findLocalByLanguage(locale));
  
  localService соответсвенно ищет необходимую строку в бд по полю
  
  LocalRepositoryImp, который imp LocalRepository c единственный методом find, пытается отсыкать строку. Если вылетает какая-либо ошибка (в основном nullp... e), то просто по дефолту присоится EN (предполагается, чтот он точно должен быть)
  
  jsp login2 вызвается -> заполняется через атрибут "local"
  
  
