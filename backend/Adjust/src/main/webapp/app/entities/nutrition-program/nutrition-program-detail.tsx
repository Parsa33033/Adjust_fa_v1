import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nutrition-program.reducer';
import { INutritionProgram } from 'app/shared/model/nutrition-program.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INutritionProgramDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NutritionProgramDetail = (props: INutritionProgramDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { nutritionProgramEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.nutritionProgram.detail.title">NutritionProgram</Translate> [<b>{nutritionProgramEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="dailyCalories">
              <Translate contentKey="adjustApp.nutritionProgram.dailyCalories">Daily Calories</Translate>
            </span>
          </dt>
          <dd>{nutritionProgramEntity.dailyCalories}</dd>
          <dt>
            <span id="proteinPercentage">
              <Translate contentKey="adjustApp.nutritionProgram.proteinPercentage">Protein Percentage</Translate>
            </span>
          </dt>
          <dd>{nutritionProgramEntity.proteinPercentage}</dd>
          <dt>
            <span id="carbsPercentage">
              <Translate contentKey="adjustApp.nutritionProgram.carbsPercentage">Carbs Percentage</Translate>
            </span>
          </dt>
          <dd>{nutritionProgramEntity.carbsPercentage}</dd>
          <dt>
            <span id="fatPercentage">
              <Translate contentKey="adjustApp.nutritionProgram.fatPercentage">Fat Percentage</Translate>
            </span>
          </dt>
          <dd>{nutritionProgramEntity.fatPercentage}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.nutritionProgram.description">Description</Translate>
            </span>
          </dt>
          <dd>{nutritionProgramEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/nutrition-program" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/nutrition-program/${nutritionProgramEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ nutritionProgram }: IRootState) => ({
  nutritionProgramEntity: nutritionProgram.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NutritionProgramDetail);
