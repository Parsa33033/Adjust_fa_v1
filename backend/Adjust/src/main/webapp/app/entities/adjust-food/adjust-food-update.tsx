import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdjustNutrition } from 'app/shared/model/adjust-nutrition.model';
import { getEntities as getAdjustNutritions } from 'app/entities/adjust-nutrition/adjust-nutrition.reducer';
import { getEntity, updateEntity, createEntity, reset } from './adjust-food.reducer';
import { IAdjustFood } from 'app/shared/model/adjust-food.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdjustFoodUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustFoodUpdate = (props: IAdjustFoodUpdateProps) => {
  const [nutritionId, setNutritionId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { adjustFoodEntity, adjustNutritions, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/adjust-food');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdjustNutritions();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...adjustFoodEntity,
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
          <h2 id="adjustApp.adjustFood.home.createOrEditLabel">
            <Translate contentKey="adjustApp.adjustFood.home.createOrEditLabel">Create or edit a AdjustFood</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adjustFoodEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="adjust-food-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="adjust-food-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="adjust-food-name">
                  <Translate contentKey="adjustApp.adjustFood.name">Name</Translate>
                </Label>
                <AvField id="adjust-food-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="adjust-food-description">
                  <Translate contentKey="adjustApp.adjustFood.description">Description</Translate>
                </Label>
                <AvField id="adjust-food-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label for="adjust-food-nutrition">
                  <Translate contentKey="adjustApp.adjustFood.nutrition">Nutrition</Translate>
                </Label>
                <AvInput id="adjust-food-nutrition" type="select" className="form-control" name="nutritionId">
                  <option value="" key="0" />
                  {adjustNutritions
                    ? adjustNutritions.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/adjust-food" replace color="info">
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
  adjustNutritions: storeState.adjustNutrition.entities,
  adjustFoodEntity: storeState.adjustFood.entity,
  loading: storeState.adjustFood.loading,
  updating: storeState.adjustFood.updating,
  updateSuccess: storeState.adjustFood.updateSuccess,
});

const mapDispatchToProps = {
  getAdjustNutritions,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustFoodUpdate);
