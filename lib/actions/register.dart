import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:meta/meta.dart';
import 'package:movie_app/models/app_user.dart';

import 'index.dart';

part 'register.freezed.dart';

@freezed
abstract class Register with _$Register implements AppAction {
  const factory Register({@required String email, @required String password, @required String displayName, @required ActionResponse response}) = Register$;

  const factory Register.successful(AppUser appUser) = RegisterSuccessful;

  @Implements(ErrorAction)
  const factory Register.error(dynamic error) = RegisterError;

}