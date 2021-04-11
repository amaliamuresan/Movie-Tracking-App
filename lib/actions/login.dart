import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:meta/meta.dart';
import 'package:movie_app/actions/index.dart';
import 'package:movie_app/models/app_user.dart';

part 'login.freezed.dart';

@freezed
abstract class Login with _$Login implements AppAction {
  const factory Login({@required String email, @required String password, @required ActionResponse response}) = Login$;

  const factory Login.successful(AppUser appUser) = LoginSuccessful;

  @Implements(ErrorAction)
  const factory Login.error(Object error) = LoginError;
}