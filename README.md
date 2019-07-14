# data-supplier-stp
Данное дополнение для Cuba Platform приложений предоставляет возможность получения различного рода информации в удободоступном виде.

##Адреса
Аддон позволяет форматировать адреса с полным получением всей информации, а так же предоставляет функциональность по подсказке вводимых адресов для пользователя.
##Банки
Аддон позволяет получать список банков по их наименованию или номеру, предоставляя полноценную информацию по ним.

Ключевыми компонентами аддона являются классы-делегаты, выполняющие непосредственное получение данных:
*com.groupstp.datasupplier.core.bean.datasupplier.provider.address.AddressDataProviderDelegate*
*com.groupstp.datasupplier.core.bean.datasupplier.provider.bank.BankDataProviderDelegate*

В дополнении на данный момент реализованы делегаты на основе сервиса DaData:
*com.groupstp.datasupplier.core.bean.datasupplier.provider.address.dadata.AddressDaDataProvider*
*com.groupstp.datasupplier.core.bean.datasupplier.provider.bank.dadata.BankDaDataProvider*

Они легко могут быть заменены любой другой реализацией, достаточно лишь указать порядок выполнения.

В проекте есть специальный компонент по подбору подсказок *com.groupstp.datasupplier.web.gui.components.AutocompleteTextField*,
так же может быть использован стандартный *com.haulmont.cuba.gui.components.SuggestionField*.

Так же есть вспомогательные классы *com.groupstp.datasupplier.web.util.AddressUiHelper* и *com.groupstp.datasupplier.web.util.BankUiHelper* которые предоставляют доступ к функционалу с UI модуля в удобном виде.