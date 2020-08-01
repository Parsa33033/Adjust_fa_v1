import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { getEntities as getAdjustPrograms } from 'app/entities/adjust-program/adjust-program.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './nutrition-program.reducer';
import { INutritionProgram } from 'app/shared/model/nutrition-program.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INutritionProgramUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NutritionProgramUpdate = (props: INutritionProgramUpdateProps) => {
  const [programId, setProgramId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { nutritionProgramEntity, adjustPrograms, loading, updating } = props;

  const { description } = nutritionProgramEntity;

  const handleClose = () => {
    props.history.push('/nutrition-program');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdjustPrograms();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...nutritionProgramEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.nutritionProgram.home.createOrEditLabel">
            <Translate contentKey="adjustApp.nutritionProgram.home.createOrEditLabel">Create or edit a NutritionProgram</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : nutritionProgramEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="nutrition-program-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="nutrition-program-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dailyCaloriesLabel" for="nutrition-program-dailyCalories">
                  <Translate contentKey="adjustApp.nutritionProgram.dailyCalories">Daily Calories</Translate>
                </Label>
                <AvField id="nutrition-program-dailyCalories" type="string" className="form-control" name="dailyCalories" />
              </AvGroup>
              <AvGroup>
                <Label id="proteinPercentageLabel" for="nutrition-program-proteinPercentage">
                  <Translate contentKey="adjustApp.nutritionProgram.proteinPercentage">Protein Percentage</Translate>
                </Label>
                <AvField id="nutrition-program-proteinPercentage" type="string" className="form-control" name="proteinPercentage" />
              </AvGroup>
              <AvGroup>
                <Label id="carbsPercentageLabel" for="nutrition-program-carbsPercentage">
                  <Translate contentKey="adjustApp.nutritionProgram.carbsPercentage">Carbs Percentage</Translate>
                </Label>
                <AvField id="nutrition-program-carbsPercentage" type="string" className="form-control" name="carbsPercentage" />
              </AvGroup>
              <AvGroup>
                <Label id="fatPercentageLabel" for="nutrition-program-fatPercentage">
                  <Translate contentKey="adjustApp.nutritionProgram.fatPercentage">Fat Percentage</Translate>
                </Label>
                <AvField id="nutrition-program-fatPercentage" type="string" className="form-control" name="fatPercentage" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="nutrition-program-description">
                  <Translate contentKey="adjustApp.nutritionProgram.description">Description</Translate>
                </Label>
                <AvInput id="nutrition-program-description" type="textarea" name="description" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/nutrition-program" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  adjustPrograms: storeState.adjustProgram.entities,
  nutritionProgramEntity: storeState.nutritionProgram.entity,
  loading: storeState.nutritionProgram.loading,
  updating: storeState.nutritionProgram.updating,
  updateSuccess: storeState.nutritionProgram.updateSuccess,
});

const mapDispatchToProps = {
  getAdjustPrograms,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NutritionProgramUpdate);
